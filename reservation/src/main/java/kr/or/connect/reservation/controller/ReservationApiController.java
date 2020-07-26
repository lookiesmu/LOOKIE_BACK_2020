package kr.or.connect.reservation.controller;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	DisplayInfoService displayInfoService;
	@Autowired
	PromotionService promotionService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	ReservationUserCommentService reservationUserCommentService;
	@Autowired
	ProductPriceService productPriceService;
	@Autowired
	DisplayInfoImageService displayInfoImageService;
	@Autowired
	ReservationInfoService reservationInfoService;
	@Autowired
	UserService userService;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Autowired
	ReservationUserCommentImageService reservationUserCommentImageService;

	@GetMapping(path = "/categories")
	public Map<String, Object> getCategories() {
		Map<String, Object> map = new LinkedHashMap<>();

		List<Category> items = categoryService.getCategory();
		int size = categoryService.getCount();

		map.put("size", size);
		map.put("items", items);

		return map;
	}

	@GetMapping(path = "/displayinfos")
	public Map<String, Object> getDisplayInfos(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "1") int start) {
		Map<String, Object> map = new LinkedHashMap<>();
		List<DisplayInfo> displayInfos = null;
		int displayCount = 0;
		int totalCount = 0;

		try {
			displayInfos = displayInfoService.getDisplayInfos(start - 1, categoryId);
			displayCount = displayInfos.size();

			if (categoryId == 0)
				totalCount = displayInfoService.getTotalCount();
			else
				totalCount = displayInfoService.getCountByCategoryId(categoryId);

			map.put("totalCount", totalCount);
			map.put("productCount", displayCount);
			map.put("products", displayInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@GetMapping(path = "/displayinfos/{displayId}")
	public Map<String, Object> getDisplayinfosById(@PathVariable(name = "displayId") int displayId) {
		Map<String, Object> map = new LinkedHashMap<>();

		try {
			DisplayInfo displayInfo = displayInfoService.getDisplayInfoById(displayId);

			int productId = displayInfo.getId();
			ProductImage productImage = productImageService.getProductImagesByProductId(productId);
			int avgScore = reservationUserCommentService.getScore(productId);

			List<ProductPrice> productPriceList = productPriceService.getPriceByProductId(productId);
			DisplayInfoImage displayInfoImage = displayInfoImageService.getDisplayInfoImagesByDisplayInfoId(displayId);

			map.put("product", displayInfo);
			map.put("productImages", productImage);
			map.put("displayInfoImages", displayInfoImage);
			map.put("avgScore", avgScore);
			map.put("productPrices", productPriceList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@GetMapping(path = "/promotions")
	public Map<String, Object> getPromotions() {
		Map<String, Object> map = new LinkedHashMap<>();

		int size = promotionService.getCount();
		List<Promotion> promotions = promotionService.getPromotion();

		map.put("size", size);
		map.put("items", promotions);

		return map;
	}

	@GetMapping(path = "/comments")
	public Map<String, Object> getComments(
			@RequestParam(name = "productId", required = false, defaultValue = "-1") int productId,
			@RequestParam(name = "start", required = false, defaultValue = "1") int start) {
		Map<String, Object> map = new LinkedHashMap<>();
		List<ReservationUserComment> commentAll = null;
		List<ReservationUserComment> commentList = null;

		try {
			int totalCount = reservationUserCommentService.getTotalCount();

			if (productId == -1) {
				commentList = reservationUserCommentService.getComment(start - 1);
			} else {
				commentList = reservationUserCommentService.getCommentByProductId(productId, start - 1);
			}
			int commentCount = commentList.size();

			int i = 0;
			for (ReservationUserComment List : commentList) {
				List.setReservationUserCommentImages(
						reservationUserCommentImageService.getReservationImage(List.getReservationInfoId()));
				commentList.set(i, List);
				i++;
			}
			
			map.put("totalCount", totalCount);
			map.put("commentCount", commentCount);
			map.put("reservationUserComments", commentList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@PostMapping(path = "/reservationInfos")
	public Map<String, Object> postReservation(
			@RequestParam(name = "productPriceId", required = true) int productPriceId,
			@RequestParam(name = "count", required = true) int count,
			@RequestParam(name = "productId", required = true) int productId,
			@RequestParam(name = "displayInfoId", required = true) int displayInfoId,
			@SessionAttribute("userId") int userId) {
		Map<String, Object> map = new LinkedHashMap<>();

		if (userId == 0) {
			map.put("error", "로그인 안함");
		}

		reservationInfoService.postReservation(productId, displayInfoId, userId);

		int reservationInfoId = reservationInfoService.getId(productId, displayInfoId, userId);
		reservationInfoService.postPrice(reservationInfoId, productPriceId, count);

		Price price = new Price();

		price.setReservationInfoId(reservationInfoId);
		price.setCount(count);
		price.setProductPriceId(productPriceId);

		map.put("prices", price);
		map.put("productId", productId);
		map.put("displayInfoId", displayInfoId);
		map.put("userId", userId);

		return map;
	}

	@GetMapping(path = "/reservationInfos") // 로그인 상태
	public Map<String, Object> getReservation(@SessionAttribute("userId") int userId, HttpServletRequest request) {
		Map<String, Object> map = new LinkedHashMap<>();

		if (userId == 0) {
			map.put("error", "로그인 안함");
		}

		List<ReservationInfo> reservationList = null;

		try {
			int size = reservationInfoService.getSize(userId);
			reservationList = reservationInfoService.getReservationInfo(userId);
			map.put("size", size);
			map.put("items", reservationList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	@PutMapping(path = "/reservationInfos") // 로그인 상태
	public Map<String, Object> deleteReservation(
			@RequestParam(name = "id", required = true, defaultValue = "1") int id) {
		Map<String, Object> map = new LinkedHashMap<>();

		String result = reservationInfoService.deleteId(id);

		map.put("result", result);
		return map;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public CustomUserDetails postLogin(@RequestParam(name = "loginUserId", required = true) String userId,
			@RequestParam(name = "password", required = true) String password, HttpSession session) {

		UserEntity user = userService.getUser(userId);
		int id = userService.getId(userId);

		if (user == null) {
			throw new UsernameNotFoundException("회원의 아이디 혹은 패스워드가 다릅니다.");
		}

		CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService
				.loadUserByUsername(user.getLoginUserId());
		session.setAttribute("userId", id);

		return customUserDetails;
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public Map<String, String> logout(HttpSession session) {
		Map<String, String> map = new LinkedHashMap<>();

		session.removeAttribute("userId");
		map.put("logout", "success");
		return map;
	}

	@PostMapping(path = "/comments")
	public Map<String, Object> postReservation(
			@RequestParam(name = "reservationInfoId", required = true) int reservationInfoId,
			@RequestParam(name = "score", required = true) int score,
			@RequestParam(name = "comment", required = true) String comment, @RequestParam("file") MultipartFile file,
			@SessionAttribute("userId") int userId) {
		Map<String, Object> map = new LinkedHashMap<>();
		int productId = reservationUserCommentImageService.getProductId(reservationInfoId);

		reservationUserCommentImageService.postComment(productId, reservationInfoId, userId, score, comment);

		String fileName = file.getOriginalFilename();
		String saveFileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		int deleteFlag = 0;

		try (
			// 맥일 경우
			// FileOutputStream fos = new FileOutputStream("/tmp/" +
			// file.getOriginalFilename());
			// 윈도우일 경우
			FileOutputStream fos = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
			InputStream is = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}

		reservationUserCommentImageService.postImage(fileName, saveFileName, contentType, deleteFlag);
		
		int reservationUserCommentId = reservationUserCommentImageService.getReservationCommentId(productId, reservationInfoId, userId);
		int fileId = reservationUserCommentImageService.getFileId(fileName);
		
		reservationUserCommentImageService.postCommentImage(reservationInfoId, reservationUserCommentId, fileId);
		
		map.put("result", "success");
		map.put("productId", productId);
		return map;
	}
	
	@GetMapping(path = "/files/{fileId}")
	public void download(@PathVariable(name = "fileId") int fileId, HttpServletResponse response) {
		// 직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
		String fileName = reservationUserCommentImageService.getFileName(fileId);
		String saveFileName = "c:/tmp/"+fileName; // 맥일 경우 "/tmp/connect.png" 로 수정
		String contentType = reservationUserCommentImageService.getFileType(fileId);
		int fileLength = 1116303;

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (FileInputStream fis = new FileInputStream(saveFileName); OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}
