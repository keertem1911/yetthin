package com.yetthin.web.commit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcFlowChargeProvinceRequest;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MessageSendToPhone {
		private static final String url="http://gw.api.taobao.com/router/rest";  
		private static final String appKey="23377438";
		private static final String secret="55decfd014ffb7a221eb25dd9168fa8a";
		private static final int Object = 0;
		 
		public static String sendToRigister(String phone,String tempId,String text){
//				String verityCode=ProductRandomSalt.getString(6);
				String verityCode=phone.substring(5);
				
				String msg=null;
				String statusCode="200";
//				TaobaoClient client =new DefaultTaobaoClient(url, appKey, secret);
//				AlibabaAliqinFcSmsNumSendRequest req=new AlibabaAliqinFcSmsNumSendRequest();
//				
//				req.setExtend("1234");
//				req.setSmsType("normal");
//				req.setSmsFreeSignName("注册验证");
//				req.setSmsParam("{\"code\":\""+verityCode+"\",\"product\":\""+text+"\"}");
//				req.setRecNum(phone);
//				req.setSmsTemplateCode("");
//				AlibabaAliqinFcSmsNumSendResponse resp=null;
//				try {
////					resp = client.execute(req);
//				} catch (ApiException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally{
					String json ="{\"alibaba_aliqin_fc_sms_num_send_response\":{\"result\":{\"err_code\":\"0\",\"model\":\"134523^4351232\",\"success\":false,\"msg\":\"成功\"}}}";
//					String json=resp.getBody();
					Class clazz=HashMap.class;
					Map map=JSON.parseObject(json,clazz);
					Object o=map.get("alibaba_aliqin_fc_sms_num_send_response");
					if(o!=null){
					map= JSON.parseObject(o.toString(),clazz);
//					String request_id=map.get("request_id").toString();
					Map<String, Object> result=JSON.parseObject(map.get("result").toString());
					boolean success=(boolean)result.get("success");
						msg="发送成功";
						statusCode="200";
						msg=result.get("err_code").toString();						
					}else{
						o=map.get("error_response");
						if(o!=null){
							map =JSON.parseObject(o.toString());
							msg=map.get("sub_msg").toString();
							msg+=map.get("sub_code").toString();
							statusCode=map.get("code").toString();
						}
					}
//				}
				
				return verityCode+","+statusCode+","+msg;
		}
		public static void main(String[] args) {
			String result=new MessageSendToPhone().sendToRigister("18829290544","SMS_10140393","投智星");
			String [] resSub=result.split(",");
			String verify=resSub[0];
			String statusCode=resSub[1];
			String msg=resSub[2];
			 System.out.println(verify+" = "+statusCode+" = "+msg);
		}
}
