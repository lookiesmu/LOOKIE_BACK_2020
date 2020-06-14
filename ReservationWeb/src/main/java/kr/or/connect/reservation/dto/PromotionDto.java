package kr.or.connect.reservation.dto;

public class PromotionDto {
		private int id;
		private String ProductImageUrl;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getProductImageUrl() {
			return ProductImageUrl;
		}
		public void setProductImageUrl(String productImageUrl) {
			ProductImageUrl = productImageUrl;
		}
		
		@Override
		public String toString() {
			return "PromotionDto [id=" + id + ", ProductImageUrl=" + ProductImageUrl + "]";
		}
		
		
		
}
