package tk.mybatis.springboot.model.vo;

import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.springboot.model.Banner;

public class BannerVo extends Banner {

    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
