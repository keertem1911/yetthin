package zcom.yetthin.web.controller;

 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.yetthin.web.domain.Admin;
import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.domain.UserInfo;
import com.yetthin.web.service.AdminService;
import com.yetthin.web.service.PhoneVersionService;
import com.yetthin.web.service.UserInfoService;

@Controller
@SessionAttributes("auth_admin_key")
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Resource(name = "UserInfoService")
	private UserInfoService userInfoService;
	@Resource(name = "PhoneVersionService")
	private PhoneVersionService phoneVersionService;
	@Resource(name = "AdminService")
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin admin, Model model, HttpServletRequest req) {
		System.out.println(admin);
		String password = getEncrty(admin.getAdminName() + "," + admin.getAdminPassword());
		admin.setAdminPassword(password);
		int i = adminService.login(admin);

		if (i > 0) {
			System.out.println("come into -------------" + i);
			model.addAttribute("auth_admin_key", i + "," + admin.getAdminName() + "," + admin.getAdminPassword());
			model.addAttribute("adminId", i);
			return "/admin/home";
		} else {
			System.out.println("goback ----------------------");
			req.getSession().setAttribute("status", i);
			return "redirect:/login.jsp";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public Map<String, Object> register(Admin admin) {
		String flag = "200";
		Map<String, Object> map = new HashMap<>();
		if (!"".equals(admin.getAdminName().trim()) && admin.getAdminName() != null && admin.getAdminPassword() != null
				&& !"".equals(admin.getAdminPassword().trim())) {

			try {
				String password = getEncrty(admin.getAdminName() + "," + admin.getAdminPassword());
				admin.setAdminPassword(password);
				admin.setAdminPower(8);
				List<Admin> admins=adminService.getListAll();
				boolean same=false;
				for(int j=0;j<admins.size();++j){
					if(admins.get(j).getAdminName().equals(admin.getAdminName())){
						same=true;
						break;
					}
				}
				if(!same){
				int i = adminService.save(admin);
				if (i == 0)
					flag = "注册失败";
				}else{
					flag="用户名重复";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		map.put("status", flag);
		return map;
	}

	public AdminController() {
		// TODO Auto-generated constructor stub

	}

	@RequestMapping(value = "/showUserinfo", method = RequestMethod.GET)
	public String selectUserAllInfo(Model model) {
		List<UserInfo> lists = userInfoService.getListAll();
		if (lists.size() == 0 || lists == null) {
			model.addAttribute("admin_error", "用户为空");
		} else {
			model.addAttribute("users", lists);
		}
		return "/admin/showUserinfo";
	}

	@ResponseBody
	@RequestMapping(value = "/selectByphoneNum/{number}", method = RequestMethod.GET)
	public Map<String, Object> selectUserByphoneNum(@PathVariable("number") String phoneNum) {
	 
		Map<String, Object> map = new HashMap<>();

		UserInfo ui = userInfoService.selectByPhoneNum(phoneNum);
		if (ui != null) {
			map.put("user", ui);
		} else {
			map.put("error", "用户不存在");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/selectByEmail", method = RequestMethod.GET)
	public Map<String, Object> selectUserByEmail(@RequestParam(value = "email") String email) {
		Map<String, Object> map = new HashMap<>();

		UserInfo ui = userInfoService.selectByEmail(email);
		if (ui != null) {
			map.put("user", ui);
		} else {
			map.put("error", "用户不存在");
		}
		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/updateNewVersionDo", method = RequestMethod.PUT)
	public String updateNewVersionDo(PhoneVersion pv) throws Exception {
		int i = phoneVersionService.save(pv);
		String flag = "200";
		return i == 0 ? "error" : flag;
	}

	@ResponseBody
	@RequestMapping(value = "/updateNewVersion2", method = RequestMethod.POST)
	public String updateNewVersiontext(@RequestParam(value = "file") MultipartFile file) {
		// try {
		// InputStream is= file.getInputStream();
		// Reader reader=new InputStreamReader(is, "utf-8");
		// BufferedReader bffer=new BufferedReader(reader);
		// String line=null;
		// while((line=bffer.readLine())!=null){
		// System.out.println(line);
		// }
		// is.close();
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		System.out.println(file.getOriginalFilename() + "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		return "200";
	}

	@RequestMapping(value = "/lookfeedback", method = RequestMethod.GET)
	public String lookUserIdeaText(Model model) {

		List<UserInfo> lists = userInfoService.lookIdeaText();
		System.out.println(Arrays.asList(lists));
		if (lists.size() != 0) {
			model.addAttribute("userIdea", lists);
		}
		return "/admin/lookfeedback";
	}

	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.PUT)
	public String changeAdminPwd(@RequestParam(value = "oldPassword") String oldPwd,
			@RequestParam(value = "newPassword") String newPwd, Model model) {
		Map<String, Object> map = model.asMap();
		String key = (String) map.get("auth_admin_key");
		String[] subStr = key.split(",");
		System.out.println(Arrays.asList(subStr));
		oldPwd = getEncrty(subStr[1] + "," + oldPwd);
		String flag = "200";
		if (subStr[2].equals(oldPwd)) {
			newPwd = getEncrty(subStr[1] + "," + newPwd);
			flag = adminService.changePwd(Integer.parseInt(subStr[0]), newPwd);
			if (flag.equals("200")) {
				model.addAttribute("auth_admin_key", subStr[0] + "," + subStr[1] + "," + newPwd);
			}
		}
		return flag;
	}

	@ResponseBody
	@RequestMapping(value = "/changeAdminname", method = RequestMethod.PUT)
	public Map<String, Object> changeAdminname(@RequestParam(value="password")String password,
			@RequestParam(value="adminName")String userName,Model model) {
		String flag="200";
		Map<String, Object> status=new HashMap<>();
		List<Admin> admins=adminService.getListAll();
		boolean same=false;
		Map<String, Object> map=model.asMap();
		String keys=(String)map.get("auth_admin_key");
		String [] subStr=keys.split(",");
		int id=Integer.parseInt(subStr[0]);
		for(Admin a:admins){
			if(a.getAdminName().equals(userName)&&a.getId()!=id){
				same=true;
				break;
			}
		}
		if(!same){
		String passwd=getEncrty(subStr[1]+","+password);
		if(subStr[2].equals(passwd.trim())){
			int i =adminService.changeName(subStr[0],userName);
			if(i==0){
				flag="更新失败";
			}
		}else{
			flag="密码错误";
		}
		}else{
			flag="用户名重复";
		}
		status.put("status", flag);
		return status;
	}

	@RequestMapping(value = "/selectInfo", method = RequestMethod.GET)
	public String selectMoreInfo() {
		return "/admin/selectInfo";
	}

	@RequestMapping(value = "/selectAllAdmin", method = RequestMethod.GET)
	public String selectAllAdmin(Model model) {
		List<Admin> admins = adminService.getListAll();
		if (admins != null) {
			model.addAttribute("admins", admins);
		} else {
			model.addAttribute("status", "error");

		}
		return "/admin/selectAllAdmin";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutAdmin(Model model) {
		model.addAttribute("auth_admin_key", "");
		return "redirect:/login.jsp";
	}
	@ResponseBody
	@RequestMapping(value = "/deleteUserById/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") String id, Model model) {
		int i = 0;
		try {
			i = userInfoService.delete(id);
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i+"";
	}

}
