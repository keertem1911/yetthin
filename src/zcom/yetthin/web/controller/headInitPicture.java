package zcom.yetthin.web.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yetthin.web.domain.HeadPicture;
import com.yetthin.web.service.HeadInitPictureService;
import com.yetthin.web.serviceImp.HeadInitPictureServiceImp;

/**
 * 活动图片上传与请求
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/picture") 
public class headInitPicture {
		@Resource
		private HeadInitPictureService headInitPictureService;
		@RequestMapping(value="/show",method=RequestMethod.GET)
		public String showList(){
			return "/admin/showList";
		}
		@ResponseBody
		@RequestMapping(value="/upload",method=RequestMethod.POST,
		produces={"application/json;charset=UTF-8"})
		public String uploadPic(@RequestParam(value="partNum")int partNum,@RequestParam(value="picture")MultipartFile pic
				,@RequestParam("href")String href,HttpServletRequest request){
			String status="200";
			String pathRoot =request.getServletContext().getRealPath("");
			String path="";
			if(!pic.isEmpty()){
				String name = "pic-"+partNum;
				String contextType =pic.getContentType();
				String imagename=pic.getOriginalFilename();
				System.out.println(imagename);
				imagename=imagename.substring(imagename.lastIndexOf(".")+1,imagename.length());
				System.out.println(imagename);
				path=pathRoot+"/image/"+name+"."+imagename;
				
				try {
					pic.transferTo(new File(path));
					HeadPicture entity= new HeadPicture();
					entity.setId(partNum);
					entity.setImgSrc("/image/"+name+"."+imagename);
					entity.setHrefUrl(href);
					int i =headInitPictureService.save(entity);
					if(i==0)
						status="-1";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					status="-1";
				}
				
				
			}else{
				status="-1";
			}
			return status;
		}
		 
		@ResponseBody
		@RequestMapping(value="/getHeadList",method=RequestMethod.POST,
				produces={"application/json;charset=utf-8"})
		public String getHeadList(HttpServletRequest req){
			StringBuffer path= req.getRequestURL();
			
			String json =headInitPictureService.getPictureList(path.toString());
		//	System.out.println(json);
			
			return "{"+json+"}";
		}
		
}
