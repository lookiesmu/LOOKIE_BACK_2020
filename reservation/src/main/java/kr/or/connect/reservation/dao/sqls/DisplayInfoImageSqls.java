package kr.or.connect.reservation.dao.sqls;

public class DisplayInfoImageSqls {
    public static final String DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "SELECT d_info.id, d_info.display_info_id, d_info.file_id, f_info.file_name, f_info.save_file_name, f_info.content_type, f_info.delete_flag, f_info.create_date, f_info.modify_date " +
            "FROM display_info_image d_info, file_info f_info " +
            "WHERE d_info.file_id=f_info.id AND display_info_id = :displayInfoId";
}
