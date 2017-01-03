package com.yetthin.web.serviceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.*;

import java.time.LocalDate;
import java.util.stream.Stream;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.domain.BarDataOfDay;
import com.yetthin.web.domain.Concept;
import com.yetthin.web.domain.DisscussInfo;
import com.yetthin.web.domain.Evaluation;
import com.yetthin.web.domain.Group;
import com.yetthin.web.domain.GroupAnalyse;
import com.yetthin.web.domain.GroupComponent;
import com.yetthin.web.domain.GroupDetail;
import com.yetthin.web.domain.GroupRecommend;
import com.yetthin.web.domain.GroupStockByStratgyLable;
import com.yetthin.web.domain.Message;
import com.yetthin.web.domain.Model2Info;
import com.yetthin.web.domain.MyAgree;
import com.yetthin.web.domain.MyCollection;
import com.yetthin.web.domain.ProfitData;
import com.yetthin.web.domain.Share;
import com.yetthin.web.domain.ShareChange;
import com.yetthin.web.domain.StockLable;
import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupget;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.domain.StockType;
import com.yetthin.web.domain.Strategy;
import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.User;
import com.yetthin.web.domain.UserGroups;
import com.yetthin.web.persistence.BarDataOfDayMapper;
import com.yetthin.web.persistence.ConceptMapper;
import com.yetthin.web.persistence.DisscussInfoMapper;
import com.yetthin.web.persistence.GroupMapper;
import com.yetthin.web.persistence.IndexPageContextMapper;
import com.yetthin.web.persistence.MyAgreeMapper;
import com.yetthin.web.persistence.MyCollectionMapper;
import com.yetthin.web.persistence.ProfitDataMapper;
import com.yetthin.web.persistence.ReasonWordMapper;
import com.yetthin.web.persistence.ShareChangeMapper;
import com.yetthin.web.persistence.ShareMapper;
import com.yetthin.web.persistence.Stocklable2ConceptMapper;
import com.yetthin.web.persistence.StrategyMapper;
import com.yetthin.web.persistence.UserMapper;
import com.yetthin.web.service.GroupService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("GroupService")
public class GroupServiceImp extends BaseService implements GroupService, ValueFormatUtil {
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private StrategyMapper strategyMapper;
	@Resource
	private IndexPageContextMapper indexPageContextMapper;
	@Resource
	private ShareMapper shareMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private DisscussInfoMapper disscussInfoMapper;
	@Resource
	private ProfitDataMapper profitDataMapper;
	@Resource
	private ConceptMapper conceptMapper;
	@Resource
	private Stocklable2ConceptMapper stocklable2ConceptMapper;
	@Resource
	private JtdoaAPIDao jtdoaAPIDao;
	@Resource
	private ReasonWordMapper reasonWordMapper;
	@Resource
	private BarDataOfDayMapper barDataOfDayMapper;
	@Resource
	private ShareChangeMapper shareChangeMapper;
	/**
	 * 点赞mapper
	 */
	@Resource
	private MyAgreeMapper myAgreeMapper;
	/**
	 * 收藏mapper
	 */
	@Resource
	private MyCollectionMapper myCollectionMapper;
	
	private static final String[] STOCK_TYPE_INDEX1 = { "股票板块", "期货品种", "期权品种", "自定义分组" };
	private static final String[][] STOCK_TYPE_INDEX2 = { { "行业板块", "概念板块", "市场板块" }, { "期货市场", "商品期货" },
			{ "指数期权标的", "股票期权标的", "期货期权标的" } };
	/**
	 * 0-9，对应沪深0、创业板1、中金2、上期3、大商4、郑商5、沪金6，中小板7 ,其他预留 stocklabel_market
	 * 
	 */
	private static final String[][] STOCK_TYPE_INDEX3 = {
			{ "农、林、牧、渔业,采矿业,制造业,电力、热力、燃气及水生产和供应业,建筑业,批发和零售业,交通运输、仓储和邮政业,住宿和餐饮业,信息传输、软件和信息技术服务业,金融业,房地产业,租赁和商务服务业,科学研究和技术服务业,水利、环境和公共设施管理业,教育,卫生和社会工作,文化、体育和娱乐业,综合,",
					"概念1,概念2,概念3,", "主板,创业板,中小板" }, // 0-2 0->0 1->1 2->7
			{ "中金,上期", // 1-0 0->2 1->3
					"大商,郑商,沪金" }, // 1-1 0->4 1->5 2->6
			{ "50ETF,180ETF(仿真),300ETF(仿真),上证50(仿真)", "股票期权1,股票期权2,股票期权3", "期货期权1,期货期权2,期货期权3" } };
	private static final String[] INDUSTRY_INDEX = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "P", "Q", "R", "S" };
	private static final String[] STOCKLABLE_TYPE = { "股票", "股指期货", "商品期货", "股票期权", "期货期权", "指数期权" };

	private List<Model2Info> readLable(int stockType, int index) {
		List<Model2Info> list = new ArrayList<>();
		if (stockType == 0 && index == 1) {// 获取概念板块的下标

			List<Concept> conceptList = conceptMapper.selectByPrimaryKeyFromAll();
			for (Concept concept : conceptList) {
				Model2Info info = new Model2Info(concept.getConceptId(), concept.getConceptName());
				list.add(info);
			}
		} else if (stockType == 0 && index == 1) {
			String[] split = STOCK_TYPE_INDEX3[stockType][index].split(",");
			Model2Info info = new Model2Info(0 + "", split[0]);
			Model2Info info1 = new Model2Info(1 + "", split[1]);
			Model2Info info2 = new Model2Info(7 + "", split[7]);

			list.add(info);
			list.add(info1);
			list.add(info2);
		} else {
			String[] split = STOCK_TYPE_INDEX3[stockType][index].split(",");
			for (int i = 0; i < split.length; i++) {

				Model2Info info = new Model2Info(i + "", split[i]);

				list.add(info);
			}
		}
		return list;
	}

	@Override
	public String getRecommend(String groupId, String path) {
		// TODO Auto-generated method stub
		String json = null;
		List<GroupRecommend> lists = null;
		List<GroupRecommend> disscussInfo = disscussInfoMapper.selectByGroupId(groupId);
		List<String> disscussIds = disscussInfo.stream().map(GroupRecommend::getDiscussinfoId).collect(toList());
		if (disscussIds.size() > 0) {
			lists = disscussInfoMapper.selectByDisscussionFromDisscuss(disscussIds);
			Map<String, List<GroupRecommend>> mapLists = lists.stream()
					.collect(groupingBy(GroupRecommend::getDiscussinfoGroupId));
			for (GroupRecommend d : disscussInfo) {
				d.setUpPersonComments(mapLists.get(d.getDiscussinfoId()));
			}
			json = JSON.toJSONString(disscussInfo);
		} else {
			json = "[]";
		}
		json = "{\"value\":" + json + "}";
		return json;
	}

	/**
	 * STOCKLABLE_TYPE={"股票","股指期货","商品期货","股票期权","期货期权","指数期权"}; INVEST_CAP = {
	 * "10万以下", "10万到100万", "100万到1000万", "1000万到1亿", "一亿以上" }; STOCK_COUNT = {
	 * "10", "20", "30", "40", "50" }; INVEST_TIME = { "日内", "30天", "90天",
	 * "360天", "两年", "三年", "长期" }; STRATEGY_TYPE = { "保守型", "稳健型", "激励型", "权变型",
	 * "稀有型" };
	 * 
	 */
	@Override
	public String getAnalyse(String groupId) {
		// TODO Auto-generated method stub
		GroupAnalyse analyse = null;
		// if(flag) list=groupMapper.getAnalyseByname(nameOrid);
		Group group = groupMapper.selectByPrimaryKey(groupId);
		List<Share> share = shareMapper.selectIndustry(groupId);
		List<String> strategyType = share.stream().map(d -> {
			int i = Integer.parseInt(d.getShareId());
			switch (i) {
			case 0:
				return "股票";
			case 1:
			case 2:
				return "期货";
			default:
				return "期权";

			}
		}).distinct().collect(toList());

		Strategy strategy = strategyMapper.selectByPrimaryKey(group.getGroupStrategyId());
		analyse = new GroupAnalyse();
		Evaluation e = new Evaluation();
		e.setNumber("0");
		e.setStatus("涨");
		e.setStatusNum("0");
		Evaluation e1 = new Evaluation();
		e1.setNumber("0");
		e1.setStatus("跌");
		e1.setStatusNum("0");
		Evaluation e2 = new Evaluation();
		e2.setNumber("0");
		e2.setStatus("整理");
		e2.setStatusNum("0");
		analyse.setWarningLevel("0");
		analyse.setNetizen_attention_rate("0");
		analyse.setMedia_attention_rate("0");
		analyse.setStockLabelNum(e);
		analyse.setStrategyAbleNum(e1);
		analyse.setWarningStockLableNum(e2);
		analyse.setGroupKindNum(strategyType.stream().collect(joining(" ")));
		analyse.setGroupStrategyCir(INVEST_TIME[Integer.parseInt(strategy.getStrategyInvestCycle())]);
		analyse.setGroupStrategyType(strategy.getStrategyType());
		analyse.setGroupCapital(INVEST_CAP[Integer.parseInt(strategy.getStrategyFundSize())]);

		String json = JSON.toJSONString(analyse);
		return json;
	}

	private String MakeJsonCommponent(Map<String, Long> map, Function<Long, Long> fun, String[] index) {
		StringBuffer buffer = new StringBuffer();
		Set<String> set = map.keySet();
		buffer.append("[");
		int i = 0;
		for (String s : set) {

			buffer.append("{");// (100*referMap.get(s)/sum)
			buffer.append(
					"\"name\":\"" + index[Integer.parseInt(s)] + "\",\"value\":\"" + fun.apply(map.get(s)) + "\"");
			buffer.append("}");
			if (i < set.size() - 1)
				buffer.append(",");
			i++;
		}
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public String getComponent(String groupId) {
		// TODO Auto-generated method stub
		GroupComponent list = null;
		list = new GroupComponent();
		/**
		 * 所选行业板块
		 */
		List<Share> referList = shareMapper.selectIndustry(groupId);
		char a = 'A';

		Map<String, Long> referMap = referList.stream().filter(d -> d.getShareId().equals("0")).map((Share in) -> {

			int cnt = in.getShareGroupId().toCharArray()[0] - a;
			if (cnt > 13)
				cnt--;
			in.setShareGroupId(Integer.toString(cnt));
			return in;
		}).collect(groupingBy(Share::getShareGroupId, counting()));
		long sum = referList.size();

		String[] industry = STOCK_TYPE_INDEX3[0][0].split(",");
		Function<Long, Long> fun1 = (b) -> (100 * b) / sum;
		String buffer = MakeJsonCommponent(referMap, fun1, industry);
		list.setReferIndustryRatio(buffer.toString());
		Map<String, Long> referKindMap = referList.stream().collect(groupingBy(Share::getShareId, counting()));
		fun1 = (b) -> b;
		buffer = MakeJsonCommponent(referKindMap, fun1, STOCKLABLE_TYPE);
		list.setReferKindRatio(buffer);

		List<Share> shares = shareMapper.selectByGroupIdList(groupId);
		Group groups = groupMapper.selectByPrimaryKey(groupId);
		double initMoney = groups.getGroupInitMoney();
		double userFulMoney = shares.parallelStream().mapToDouble(Share::getShareStartFund).sum();

		list.setSumFund(initMoney + "");
		list.setFreeFund(Double.toString((initMoney - userFulMoney)));
		String json = tojson(list);

		return json;
	}

	@Override
	public String getDetail(String groupId, String path,String userId) {
		// TODO Auto-generated method stub
		Group group = null;
		boolean samePerson =false;
		boolean sameCollection=false;
		GroupDetail detail = new GroupDetail();
		List<Share> shareList = null;
		group = groupMapper.selectByPrimaryKey(groupId);
		if(!userId.trim().equals("")){ 
		List<MyAgree> myAgrees=myAgreeMapper.selectByUserId(userId);
		List<String> myCollection= myCollectionMapper.selectByUserId(userId);
		for (String string : myCollection) {
			if(string.trim().equals(groupId.trim())){
				sameCollection=true;
				break;
			}
		}
		/**
		 * 点赞人重复标记位 默认不重复
		 */
		
		for (MyAgree myAgree : myAgrees) {
			if(groupId.trim().equals(myAgree.getAgreeGroupId().trim())){
				samePerson=true;
				break;
			}
		}
		}
		detail.setIsMyAgree(samePerson+"");
		detail.setIsMyCollection(sameCollection+"");
		String json=null;
		if(group!=null){
		shareList = shareMapper.selectByGroupIdList(groupId);
		int MyAgreeNum = myAgreeMapper.selectCountByGroupId(groupId);
		detail.setMyAgreeNum(MyAgreeNum+"");
		detail.setGroup(group);
		if (format_double_1.format((group.getGroupIncomeTotle() - group.getGroupInitMoney())).equals("0") == false) {
			detail.setTotleIncome(
					format_double_3.format((group.getGroupIncomeTotle() - group.getGroupInitMoney()) / 10000));
			detail.setTotleIncomeRatio(format_double_3.format(
					100 * ((group.getGroupIncomeTotle() - group.getGroupInitMoney()) / group.getGroupInitMoney())));
		} else {
			detail.setTotleIncome("0");
			detail.setTotleIncomeRatio("0.00");
		}
		User user = userMapper.selectByPrimaryKey(group.getGroupCreateId());
		detail.setUser(user);
		detail.setUserImg(user.getUserHeadPic());
		List<ProfitData> profitDatas = profitDataMapper.selectByGroupIdFromDay(groupId);
		if (profitDatas.size() != 2)
			detail.setDayIncome("0");
		else {
			double todayIncome = profitDatas.get(0).getProfitdataShareFundNum();
			double yestIncome = profitDatas.get(1).getProfitdataShareFundNum();
			if (yestIncome != 0)
				detail.setDayIncome(format_double_3.format((todayIncome - yestIncome) * 100 / yestIncome));
			else
				detail.setDayIncome("0");
		}
		ProfitData profitDataCurrentDay = profitDataMapper.selectByGroupIdFromCuurentDay(groupId);
		ProfitData profitDataLastMonth = profitDataMapper.selectByGroupIdFromLastMonth(groupId);
		if (profitDataCurrentDay == null || profitDataLastMonth == null)
			detail.setMonthIncome("0");
		else {
			double todayIncome = profitDataCurrentDay.getProfitdataShareFundNum();
			double yestIncome = profitDataLastMonth.getProfitdataShareFundNum();

			detail.setMonthIncome(format_double_3.format((todayIncome - yestIncome) * 100 / yestIncome));
		}

		ProfitData profitDataNear3Month = profitDataMapper.selectByGroupIdFromNear3Month(groupId);
		if (profitDataCurrentDay == null || profitDataNear3Month == null)
			detail.setNear3MonthIncome("0");
		else {
			double todayIncome = profitDataCurrentDay.getProfitdataShareFundNum();
			double yestIncome = profitDataNear3Month.getProfitdataShareFundNum();

			detail.setNear3MonthIncome(format_double_3.format((todayIncome - yestIncome) * 100 / yestIncome));
		}
		Share share = shareMapper.selectByGroupIdOne(groupId);
		ShareChange sc = shareChangeMapper.selectLastChangeTime(share.getShareId());
		if (sc != null)
			detail.setLatestChangeShareTime(sc.getSharechangeTime());
		else
			detail.setLatestChangeShareTime(group.getGroupCreateTime());
		ProfitData createProfit = profitDataMapper.selectByGroupIdFromCreateTime(groupId);
		if (createProfit == null) {
			detail.setTotleIncome("0");
			detail.setNetIncome("0");
		} else {
			double createPrice = createProfit.getProfitdataShareFundNum();
			if (createPrice == 0) {
				detail.setNetIncome("0");
			} else {
				if (group.getGroupInitMoney() != 0)
					detail.setNetIncome(format_double_3.format(createPrice / group.getGroupInitMoney()));
				else
					detail.setNetIncome("0");
			}
		}

		if (shareList != null) {
			List<Share> incomeShare=shareList.stream().map(s->{
				s.setShareStartFund(100*((s.getShareCurrentIncome()-s.getShareStartFund())/s.getShareStartFund()));
				return s;
				}).collect(toList());
			Map<String, List<Share>> incomeRatioShare= incomeShare.parallelStream().collect(groupingBy(Share::getShareStocklableId));
			Map<String, Double> share_Start_Currnt = shareMapper.selectSumFundByGroupId(groupId);
			double sum = group.getGroupInitMoney() - share_Start_Currnt.get("start_fund")
					+ share_Start_Currnt.get("current_income");

			Map<String, Optional<Share>> radioMap = shareList.parallelStream().map(d -> {
				d.setShareCurrentIncome(d.getShareCurrentIncome() * 100 / sum);
				return d;
			}).collect(
					groupingBy(Share::getShareStocklableId, maxBy(Comparator.comparing(Share::getShareCurrentIncome))));
			Set<String> key = radioMap.keySet();
			StringBuffer buffer = new StringBuffer();
			buffer.append("[");
			int i = 0;
			Map<String, String> stockPriceMap= jtdoaAPIDao.getAllSymbolValueList();
			for (String string : key) {
				String [] subStock =stockPriceMap.get(string).split(SPLIT_STR);
				boolean plus =true;
				 
				if(subStock[2].indexOf("-")!=-1){
					plus=false;
					subStock[2]=subStock[2].substring(1);
				}
				double shareIncomeS= incomeRatioShare.get(string).get(0).getShareStartFund();
				
				buffer.append("{\"name\":\"" + radioMap.get(string).get().getShareStocklableName() + "\",\"code\":\""
						+ string + "\",\"value\":\""
						+ format_double_3.format(radioMap.get(string).get().getShareCurrentIncome()) + "\","
								+ "\"lastPrice\":\""+subStock[0]+"\",\"upOrDownPrice\":\""+subStock[2]+"\","
										+ "\"increase\":\""+plus+"\","
												+ "\"incomeRadio\":\""+format_double_3.format(shareIncomeS)+"\"}");
				if (i < key.size() - 1)
					buffer.append(",");
				i++;
			}
				
			buffer.append("]");
			detail.setStockLableList(buffer.toString());
		} else
			detail.setStockLableList("");
		json = tojson(detail);
		json = json.replace("\"[", "[");
		json = json.replace("]\"", "]");
		}else{
			json ="{\"status\":\"500\",\"item\":\"groupid不存在\",\"value\":[]}";
		}
		return json;
	}

	/**
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String stockOfGroupSave(StockOfGroup stockOfGroup) {
		System.out.println(stockOfGroup);
		// TODO Auto-generated method stub
		List<Share> stockSaves = new ArrayList<>();
		String json =null;
		 if(!"".equals(stockOfGroup.getStock().trim())){
		JSONObject jsonobject = JSONObject.fromObject("{ \"value\":" + stockOfGroup.getStock().trim() + "}");
		JSONArray jsonarray = jsonobject.getJSONArray("value");
		int initMoney = Integer.parseInt(stockOfGroup.getInitMoney());

		System.out.println(java.util.Arrays.asList(stockSaves));
		 
		Group group = new Group();
		group.setGroupId(getUUID());
		group.setGroupName(stockOfGroup.getGroupName());
		group.setGroupCreateId(stockOfGroup.getUserID());
		group.setGroupCreateTime(LocalDate.now().format(format_yyyyMMdd));
		group.setGroupInitMoney(Integer.parseInt((stockOfGroup.getInitMoney())));
		group.setGroupEvaluateLevel("C");
		group.setGroupStrategyId("1");
		group.setGroupReferIndex(stockOfGroup.getIndexCode());
		group.setGroupEmotionIndex(0);
		group.setGroupIncomeTotle(Integer.parseInt((stockOfGroup.getInitMoney())));
		group.setGroupOpen(stockOfGroup.getIfOpen());
		group.setGroupWarningLevel(0);
		group.setGroupMediaAttentionRate(0);
		group.setGroupNetizenAttentionRate(0);
		/**
		 * 解析股票JSON
		 * 
		 */
		Map<String, String> stockCode_Value = jtdoaAPIDao.getAllSymbolValueList();
		for (int i = 0; i < jsonarray.size(); ++i) {
			Object subjson = jsonarray.get(i);
			JSONObject object = JSONObject.fromObject(subjson);
			Share s = new Share();
			s.setShareStocklableId((object.getString("stockCode")).trim());
			double fund = initMoney * Double.parseDouble(object.getString("stockRatio").trim()) / 100;
			s.setShareStartFund(fund);
			s.setShareCurrentIncome(s.getShareStartFund());
			s.setShareGroupId(group.getGroupId().trim());
			s.setShareStocklableName(object.getString("stockName").trim());
			double lastPrice = 0l;

			String values = stockCode_Value.get(s.getShareStocklableId());
			if (values != null)
				if (s.getShareStocklableId().split(SPLIT_STR).length > 0
						&& !values.split(SPLIT_STR)[0].split("[.]")[0].equals("0")) {
					lastPrice = Double.parseDouble(values.split(SPLIT_STR)[0]);
					String number = format_double_3.format(s.getShareStartFund() / lastPrice);
					s.setShareCurrentNum(Double.parseDouble(number));
				} else {
					s.setShareCurrentNum(0.0);
				}
			s.setShareId(getUUID());
			if (lastPrice != 0l)
				stockSaves.add(s);
		}
		int status = groupMapper.insertSelective(group);
		status = shareMapper.insert(stockSaves);

		if (status != 0)
			json = "{\"status\":\"200\"}";
		else
			json = "{\"status\":\"500\"}";
		 }else{
			 json ="{\"status\":\"500\",\"item\":\"stock不能为空\",\"value\":[]}";
		 }
			 
		return json;
	}
	 
	// * 0-9，对应沪深0、创业板1、中金2、上期3、大商4、郑商5、沪金6，中小板7 ,其他预留 stocklabel_market
	/**
	 * "沪市,深市" }, // 0-2 0->0 1->1 { "中金,上期", // 1-0 0->2 1->3 "大商,郑商,沪金" }, //
	 * 1-1 0->4 1->5 2->6
	 * 
	 * @param map
	 * @return
	 */
	private List<StockLable> selectStockBysql(Map<String, Object> map, int type, int limit) {
		GroupStockByStratgyLable lable = new GroupStockByStratgyLable();
		List<StockLable> lists = null;
		List<String> marketList = null;
		if (map != null) {

			String str = (String) map.get("0-0");
			if (str != null) {
				String[] indexs = str.split(",");

				List<String> industry = Stream.of(indexs).map(d -> INDUSTRY_INDEX[Integer.parseInt(d)])
						.collect(toList());
				if (industry.size() == 0)
					industry = null;
				lable.setIndustry(industry);
			} else {
				lable.setIndustry(null);
			}
			lable.setStatus("1");
			marketList = new ArrayList<>();
			if (map.get("0-2") != null) {
				String industryIndex = (String) map.get("0-2");
				List<String> industryIndexList = Stream.of(industryIndex.split(",")).collect(toList());
				if (industryIndexList.size() == 0)
					industryIndexList = null;
				else {

					lable.setStockMarket(industryIndexList);

				}
			}
			String[] index = { "1-0", "1-1" };
			for (int i = 0; i < index.length; i++) {
				String m = (String) map.get(index[i]);
				if (m != null) {
					String[] ms = m.split(",");

					int indexfor = 2;
					if (i == 1)
						indexfor = 4;
					for (String string : ms)
						marketList.add(Integer.toString(Integer.parseInt(string) + indexfor));
				}

			}
			if (marketList.size() == 0)
				marketList = null;
			lable.setMarkets(marketList);
		}
		if (lable.getIndustry() == null && lable.getMarkets() == null && lable.getStockMarket() == null)
			lists = new ArrayList<>();
		else
			lists = indexPageContextMapper.getStockByStrategy(lable);
		List<StockLable> conceptList = null;
		if (map.get("0-1") != null) {
			String[] concepts = ((String) map.get("0-1")).split(",");
			List<String> conceptIds = Arrays.asList(concepts);
			conceptList = stocklable2ConceptMapper.selectStockByConceptId(conceptIds);
			if (lists.size() > 0) {
				Map<String, Boolean> oneStock = new HashMap<>();
				lists.stream().forEach(sl -> {
					oneStock.put(sl.getStocklableCode(), true);
				});

				conceptList = conceptList.stream().filter(d -> {
					String s= d.getStocklableCode();
					Boolean b = oneStock.get(s);
					if(b==null) return false;
					else return true;
				}).collect(toList());
			}

			lists = conceptList.stream().filter(d -> d.getStocklableType().equals("0")).distinct()
					.sorted(Comparator.comparing(StockLable::getStocklableCode)).collect(toList());
//			List<BarDataOfDay> listAvg = barDataOfDayMapper.selectByDates("2016-12-01","2016-12-10");
		}
		return lists;
	}

	private static final String[] INVEST_CAP = { "10万以下", "10万到100万", "100万到1000万", "1000万到1亿", "一亿以上" };
	private static final String[] STOCK_COUNT = { "10", "20", "30", "40", "50" };
	private static final String[] INVEST_TIME = { "日内", "30天", "90天", "360天", "两年", "三年", "长期" };
	private static final String[] STRATEGY_TYPE = { "保守型", "稳健型", "激励型", "权变型", "稀有型" };

	/**
	 * 模拟比例
	 * 
	 * @param size
	 * @return
	 */
	private double[] modelRadio(int size) {

		Random random = new Random();
		double[] radio = new double[size];
		double r= 80.0/(size+0.0);
		for(int i =0;i<size;++i)
			radio[i]=r;

		return radio;
	}

	@Override
	public String getStockofGroup(StockOfGroupreq req) {
		
		
		 
		// TODO Auto-generated method stub
		String stocklable = req.getSelectedModels();
		Map<String, Object> stockSelectList = null;
		
		if (stocklable != null && !"".equals(stocklable)) {
			stockSelectList = new HashMap<>();
			// 1-0:2,3;1-1:1,2;
			String[] Level1Index = stocklable.split(";");
			for (String string : Level1Index) {
				String[] keyValue = string.split(":");
				stockSelectList.put(keyValue[0], keyValue[1]);
			}
			Map<String, String> keyValueSelect = new HashMap<>();
			keyValueSelect.put("inverstCap", INVEST_CAP[req.getInvestCap()]);
			keyValueSelect.put("stockCount", STOCK_COUNT[req.getStockCount()]);
			keyValueSelect.put("investTime", INVEST_TIME[req.getInvestTime()]);
			keyValueSelect.put("strategyType", STRATEGY_TYPE[req.getStrategyID()]);
		}

		// 筛选结果
		List<StockLable> stocks = selectStockBysql(stockSelectList, req.getStrategyID(), req.getStockCount());

		// 获取这些股票的 K线数据
		List<StockOfGroupget> lists = new ArrayList<>();
		if (lists.size() == 0) {
			int size = Integer.parseInt(STOCK_COUNT[req.getStockCount()]);
			double[] radio = modelRadio(size);
			if (size > stocks.size())
				size = stocks.size();

			for (int i = 0; i < stocks.size() && i < Integer.parseInt(STOCK_COUNT[req.getStockCount()]); ++i) {
				StockOfGroupget get = new StockOfGroupget();
				get.setStockCode(stocks.get(i).getStocklableCode());
				get.setStockName(stocks.get(i).getStocklableName());
				get.setStockRatio(format_double_3.format(radio[i]));
				get.setStockType(stocks.get(i).getStocklableType());
				lists.add(get);
			}
		}
		String json = tojson(lists);
		json = "{" + json + "}";
		return json;
	}

	@Override
	public String getStockTypeList(int stockType) {
		// TODO Auto-generated method stub
		String json = null;
		if(stockType<STOCK_TYPE_INDEX2.length){
		json = "{\"stockType\":\"" + stockType + "\",\"value\":[";

		for (int i = 0; i < STOCK_TYPE_INDEX2[stockType].length; ++i) {
			List<Model2Info> lists = null;
			lists = readLable(stockType, i);// 0 1
			String json1 = tojson(lists);
			json1 = "{\"modelTypeIndex\":\"" + i + "\",\"modelTypeName\":\"" + STOCK_TYPE_INDEX2[stockType][i] + "\","
					+ json1 + "}";

			json += json1;
			if (i != STOCK_TYPE_INDEX2[stockType].length - 1)
				json += ",";
		}
		json += "]}";
		}else{
			json ="{\"status\":\"500\",\"stockType\":\""+stockType+"\",\"item\":\"stockType不存在\",\"value\":[]}";
		}
		return json;
	}

	@Override
	public String getSummarize(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		String json = "\"value\":[]";
		int totlePage = groupMapper.getTotlePageUserAll();
		 
		int[] pageA = partitionPage(totlePage, pageNum, pageSize);

		Map<String, Object> map = new HashMap<>();

		map.put("begin", pageA[0]);
		map.put("end", pageA[1]);
		

		List<UserGroups> groups = groupMapper.selectByPrimaryKeySimple(map);
		if (groups.size() > 0) {
			List<String> commendList = groups.stream().map(UserGroups::getGroupId).collect(toList());
			List<DisscussInfo> disscussInfo = disscussInfoMapper.selectByGroupIdFromList(commendList);
			groups= groups.stream().map(g->{
				int count = myAgreeMapper.selectCountByGroupId(g.getGroupId());
				g.setMyAgreeNum(count+"");
				return g;
			}).collect(toList());
			if (disscussInfo.size() > 0) {
				Map<String, Integer> countDisscuss = disscussInfo.stream()
						.collect(groupingBy(DisscussInfo::getDiscussinfoGroupId, reducing(0, d -> 1, Integer::sum)));

				groups = groups.stream().map(d -> {
					d.setRecommentNum("0");
					if (countDisscuss != null && countDisscuss.get(d.getGroupId()) != null) {
						d.setRecommentNum(Integer.toString(countDisscuss.get(d.getGroupId())));
					}
					double sum = 0;
					if (d.getGroupInitMoney() != 0 && Long.parseLong(d.getImcomeRatio()) - d.getGroupInitMoney() != 0) {
						double temp = Double.parseDouble(d.getImcomeRatio()) - d.getGroupInitMoney();
						sum = temp / d.getGroupInitMoney();
						  sum=sum*100;
					}
					d.setImcomeRatio(format_double_3.format(sum));

					return d;
				}).collect(toList());
				json = tojson(groups);
				json = json.replaceAll(",{2,10}", ",");
			} else {
				groups = groups.stream().map(d -> {
					d.setRecommentNum("0");

					double sum = 0;
					if (d.getGroupInitMoney() != 0 && Long.parseLong(d.getImcomeRatio()) - d.getGroupInitMoney() != 0) {
						double temp = Double.parseDouble(d.getImcomeRatio()) - d.getGroupInitMoney();
						sum = temp / d.getGroupInitMoney();
					}
					d.setImcomeRatio(format_double_3.format(sum));
					return d;
				}).collect(toList());
				json = tojson(groups);
				json = json.replaceAll(",{2,10}", ",");
			}
		}
		  
		json = "{\"totlePageSize\":\"" + totlePage + "\",\"currentPage\":\"" + pageNum + "\"," + json + "}";
		return json;

	}

	@Override
	public String getStockType() {
		// TODO Auto-generated method stub
		List<StockType> list = new ArrayList<StockType>();
		for (int i = 0; i < 3; i++) {
			StockType type = new StockType();
			type.setStockType(i + "");
			type.setTypeName(STOCK_TYPE_INDEX1[i]);
			list.add(type);
		}
		String json = tojson(list);
		return "{" + json + "}";

	}

	/**
	 * 保存 评论/回复 upRecommendUserId -| 0---------- groupNameOrId = groupId 组合Id |
	 * userId ------- groupNameOrId= recommendId 评论Id
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public String saveRecommend(String groupNameOrId, String upRecommendUserId, String context, String userId) {
		// TODO Auto-generated method stub
		String json = null;
		Group group = groupMapper.selectByPrimaryKey(groupNameOrId);
		User user = userMapper.selectByPrimaryKey(userId);
		if(group!=null&&user!=null){
		DisscussInfo disscussinfo = new DisscussInfo();
		disscussinfo.setDiscussinfoContent(context);
		disscussinfo.setDiscussinfoCreateTime(System.currentTimeMillis() + "");
		disscussinfo.setDiscussinfoGroupId(groupNameOrId);
		disscussinfo.setDiscussinfoHigherId(upRecommendUserId);
		disscussinfo.setDiscussinfoId(getUUID());
		disscussinfo.setDiscussinfoSendpersonId(userId);

		int i = disscussInfoMapper.insertSelective(disscussinfo);
		
		if (i != 0)
			json = "{\"status\":\"200\"}";
		else
			json = "{\"status\":\"520\"}";
		}else
			json ="{\"status\":\"500\",\"item\":\"groupid或userId不存在\"}";
		return json;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int deleteMyGroups(String userId, String groupId) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("groupId", groupId);
		Group group = groupMapper.selectGroupByUserId(map);
		int i = 0;
		if (group != null) {
			i = shareMapper.deleteByGroupId(groupId);
			i += profitDataMapper.deleteByGroupId(groupId);
			i += reasonWordMapper.deleteByGroupId(groupId);
			String d_id = disscussInfoMapper.selectByGroupIdFindDissinfo(groupId);
			if (d_id != null && !"".equals(d_id.trim()))
				i += disscussInfoMapper.deleteByGroupId(d_id);

			i += groupMapper.deleteByPrimaryKey(group.getGroupId());

		}
		return i;
	}
	/**
	 * 保存点赞操作
	 */
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public String saveMyAgree(String groupId, String userId) {
		// TODO Auto-generated method stub
		Message message= new Message();
		Group group = groupMapper.selectByPrimaryKey(groupId);
		List<MyAgree> myAgrees=myAgreeMapper.selectByGroupId(groupId);
		/**
		 * 点赞人重复标记位 默认不重复
		 */
		boolean samePerson =false;
		if(myAgrees!=null&&myAgrees.size()!=0){
		/**
		 * 点赞人不重复
		 */
		for (MyAgree myAgree : myAgrees) {
			if(userId.trim().equals(myAgree.getAgreeUserId().trim())){
				samePerson=true;break;
				}
		}//for
		}
		if(samePerson){
				message.setInfo("一个人只能点赞一次");
				message.setStatus("500");
				message.setValue(null);
				return JSON.toJSONString(message);
		}
		MyAgree agree=new MyAgree();
		agree.setAgreeGroupId(groupId);
		agree.setAgreeUserId(userId);
		int i =myAgreeMapper.insert(agree);
		if(i==0){
			message.setInfo("点赞失败");
			message.setStatus("500");
			message.setValue(null);
		}else{
			message.setInfo("点赞成功");
			message.setStatus("200");
			message.setValue("1");
		}
		return JSON.toJSONString(message);
	}

	@Override
	public String getMyGroupAgreeNumByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public String deleteMyAgreeByGroupIdAndUserId(String groupId, String userId) {
		// TODO Auto-generated method stub
		Map<String, String> map =new HashMap<>();
		map.put("groupId", groupId);
		map.put("userId", userId);
		MyAgree agree =myAgreeMapper.selectByGroupIdAndUserId(map);
		Message msg= new Message();
		if(agree!=null){
			int i = myAgreeMapper.deleteByPrimaryKey(agree.getAgreeId());
			if(i==0){
				msg.setStatus("500");
				msg.setInfo("点赞取消失败");
				
			}else{
				msg.setStatus("200");
				msg.setInfo("点赞取消成功");
				msg.setValue("1");
			}
		}else{
			msg.setInfo("点赞人为空");
			msg.setStatus("522");
			msg.setValue(null);
		}
		
		return JSON.toJSONString(msg);
	}
	@Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
	@Override
	public String saveMyCollection(String groupId,String userId) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(userId);
		Message msg= new Message();
		Group group = groupMapper.selectByPrimaryKey(groupId);
		if(user!=null&&group!=null){
		/**
		 * 查找用户所有的组合
		 */
		List<Group> groups = groupMapper.selectByUserId(userId);
		/**
		 * 去重 ，用户不能收藏自己的组合
		 */
		boolean sameGroup=false;
		for (Group group1 : groups) {
			if(group1.getGroupId().trim().equals(groupId.trim())){
				sameGroup=true;
				break;
			}
		}
		if(sameGroup){
			msg.setInfo("不能收藏自己的组合");
			msg.setStatus("500");
		}else{
			MyCollection myCollection=new MyCollection();
			myCollection.setCollectionGroupId(groupId);
			myCollection.setCollectionUserId(userId);
			/**
			 * 保存收藏 
			 */
			int i =myCollectionMapper.insert(myCollection);
			if(i==0){
				msg.setInfo("收藏失败");
				msg.setStatus("501");
			}else{
				msg.setInfo("收藏成功");
				msg.setStatus("200");
				msg.setValue("1");
			}
		}
		
		}else{
			if(user==null)
			msg.setInfo("用户不存在");
			else if(group==null)
				msg.setInfo("组合不存在");
			msg.setStatus("500");
		}
		return JSON.toJSONString(msg);
	}
	/**
	 * 取消收藏
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public String cancelMyCollection( String groupId,String userId) {
		// TODO Auto-generated method stub
		Message msg= new Message();
		/**
		 * 查找 组合是否存在
		 */
		Map<String, String> map =new HashMap<>();
		map.put("groupId", groupId);
		map.put("userId", userId);
		MyCollection myCollection = myCollectionMapper.selectByUserIdAndGroupId(map);
		if(myCollection!=null){
			int i =myCollectionMapper.deleteByPrimaryKey(myCollection.getCollectionId());
			if(i==0){
				msg.setInfo("取消收藏失败");
				msg.setStatus("500");
			}else{
				msg.setInfo("取消收藏成功");
				msg.setStatus("200");
				msg.setValue("1");
			}
		}else{
			msg.setInfo("收藏的组合不存在");
			msg.setStatus("500");
		}
		return JSON.toJSONString(msg);
	}

	 

	@Override
	public String getSummarizeFromMyCollection(String userId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String json = "\"value\":[]";
				int totlePage = groupMapper.getTotlePageUserAll();
				 
				int[] pageA = partitionPage(totlePage, pageNum, pageSize);

				Map<String, Object> map = new HashMap<>();

				List<String> groupIdList = myCollectionMapper.selectByUserId(userId);
				if(groupIdList.size()>0){
				map.put("begin", pageA[0]);
				map.put("end", pageA[1]);
				map.put("list", groupIdList);
				
				List<UserGroups> groups= groupMapper.selectByGroupIds(map);
//				List<UserGroups> groups = groupMapper.selectByPrimaryKeySimple(map);
				if (groups.size() > 0) {
					List<String> commendList = groups.stream().map(UserGroups::getGroupId).collect(toList());
					List<DisscussInfo> disscussInfo = disscussInfoMapper.selectByGroupIdFromList(commendList);
					groups= groups.stream().map(g->{
						int count = myAgreeMapper.selectCountByGroupId(g.getGroupId());
						g.setMyAgreeNum(count+"");
						return g;
					}).collect(toList());
					if (disscussInfo.size() > 0) {
						Map<String, Integer> countDisscuss = disscussInfo.stream()
								.collect(groupingBy(DisscussInfo::getDiscussinfoGroupId, reducing(0, d -> 1, Integer::sum)));

						groups = groups.stream().map(d -> {
							d.setRecommentNum("0");
							if (countDisscuss != null && countDisscuss.get(d.getGroupId()) != null) {
								d.setRecommentNum(Integer.toString(countDisscuss.get(d.getGroupId())));
							}
							double sum = 0;
							if (d.getGroupInitMoney() != 0 && Long.parseLong(d.getImcomeRatio()) - d.getGroupInitMoney() != 0) {
								double temp = Double.parseDouble(d.getImcomeRatio()) - d.getGroupInitMoney();
								sum = temp / d.getGroupInitMoney();
								  sum=sum*100;
							}
							d.setImcomeRatio(format_double_3.format(sum));

							return d;
						}).collect(toList());
						json = tojson(groups);
						json = json.replaceAll(",{2,10}", ",");
					} else {
						groups = groups.stream().map(d -> {
							d.setRecommentNum("0");

							double sum = 0;
							if (d.getGroupInitMoney() != 0 && Long.parseLong(d.getImcomeRatio()) - d.getGroupInitMoney() != 0) {
								double temp = Double.parseDouble(d.getImcomeRatio()) - d.getGroupInitMoney();
								sum = temp / d.getGroupInitMoney();
							}
							d.setImcomeRatio(format_double_3.format(sum));
							return d;
						}).collect(toList());
						json = tojson(groups);
						json = json.replaceAll(",{2,10}", ",");
					}
				}
				}else{
					json ="\"value\":[]";
				}
				json = "{\"totlePageSize\":\"" + totlePage + "\",\"currentPage\":\"" + pageNum + "\"," + json + "}";
				return json;
	
	 
	}

}
