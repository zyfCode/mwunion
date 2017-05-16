package com.sungan.ad.service.adhost.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sungan.ad.common.dao.UpdateByCondition;
import com.sungan.ad.dao.*;
import com.sungan.ad.dao.model.*;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderPayType;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderServiceImpl implements AdHostAccountAdOrderService{
	
	@Autowired
	private AdHostAccountAdOrderDAO adHostAccountAdOrderDAO;
	@Autowired
	private AdHostDAO adHostDAO;

	@Autowired
	private AdHostAccountAdOrderSourcesDAO sourcesDAO;

	@Autowired
	private AdHostAccountDAO adHostAccountDAO;

	@Autowired
	private PlatProductDAO platProductDAO;

	@Autowired
	private AdHostAccountAdOrderAttriDAO  attriDAO;


	public AdHostAccountAdOrderDAO getAdHostAccountAdOrderDAO() {
		return adHostAccountAdOrderDAO;
	}

	public void setAdHostAccountAdOrderDAO(AdHostAccountAdOrderDAO adHostAccountAdOrderDAO) {
		this.adHostAccountAdOrderDAO = adHostAccountAdOrderDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrder record,AdHostAccountAdOrderAttri attri,List<String> anaxFiles) {
		String productId = record.getProductId();
		if(StringUtils.isBlank(record.getProductId())){
			throw new AdRuntimeException("请选择产品");
		}
		PlatProduct platProduct = platProductDAO.find(record.getProductId());
		if(platProduct==null){
			throw new AdRuntimeException("产品不存在:"+productId);
		}
		if(StringUtils.isBlank(record.getAdHostId())){
			throw new AdRuntimeException("请选择站长");
		}
		AdHost adHost = adHostDAO.find(record.getAdHostId());
		if(adHost==null){
			throw new AdRuntimeException("请选择站长");
		}
		String nextId = IdGeneratorFactory.nextId();
		record.setAdOrderId(nextId);
		record.setAdHostAmount(BigDecimal.ZERO);
		record.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.UNSURE.getKey());
		record.setProductName(platProduct.getProductName());
		record.setAdOrderType(platProduct.getProductType());
		record.setVersion(0);
		record.setAdHostName(adHost.getAdhostName());
		AdHostAccount condition = new AdHostAccount();
		condition.setAdHostId(adHost.getAdhostId());
		List<AdHostAccount> query = (List<AdHostAccount>) adHostAccountDAO.query(condition);
		if(query.size()<0){
			throw new AdRuntimeException("站长账号不存在");
		}
		AdHostAccount adHostAccount = query.get(0);
		record.setAccountId(adHostAccount.getAccountId());
		record.setHitCount(0L);
		record.setShowCount(0L);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());


		record.setProductName(platProduct.getProductName());
		attri.setAccountId(record.getAccountId());
		attri.setAdHostId(record.getAdHostId());
		attri.setAdOrderAtrriId(IdGeneratorFactory.nextId());
		attri.setAdOrderId(record.getAdOrderId());
		attri.setAdOrderAtrriId(IdGeneratorFactory.nextId());
		attri.setAccountId(adHostAccount.getAccountId());
		attri.setAdAndoriPrice(platProduct.getAdAndoriPrice());
		attri.setAdISOPrice(platProduct.getAdISOPrice());
		attri.setAdPCPrice(platProduct.getAdPCPrice());
		attri.setStAndriodPrice(platProduct.getStAndriodPrice());
		attri.setStIOSPrice(platProduct.getStIOSPrice());
		attri.setStPCPrice(platProduct.getAdPCPrice());
		attri.setPublicSource(platProduct.getPublicSource());
		attri.setCreateTime(new Date());
		attri.setUpdateTime(new Date());
		attriDAO.insert(attri);
		adHostAccountAdOrderDAO.insert(record);
		if(anaxFiles!=null&&anaxFiles.size()>0){
			this.insertSource(anaxFiles,record);
		}
		return nextId;
	}

	private void insertSource(List<String> files,AdHostAccountAdOrder order){
		List<AdHostAccountAdOrderSources> orders = new ArrayList<>();
		for (String fileName:files) {
			AdHostAccountAdOrderSources sources = new AdHostAccountAdOrderSources();
			sources.setAccountId(order.getAccountId());
			sources.setAdHostId(order.getAdHostId());
			sources.setAdOrderId(order.getAdOrderId());
			sources.setCreateTime(new Date());
			sources.setSourceId(IdGeneratorFactory.nextId());
			sources.setSourceName(fileName);
			sources.setSourcePath("");
			sources.setSourceType("");
			sources.setSourceUrl("");
			sources.setUpdateTime(new Date());
			orders.add(sources);
		}
		this.sourcesDAO.insert(orders);
	}

	@Override
	public List<AdHostAccountAdOrderVo> queryList(AdHostAccountAdOrder condition) {
		List<AdHostAccountAdOrder> query = (List<AdHostAccountAdOrder>) adHostAccountAdOrderDAO.query(condition);
		 List<AdHostAccountAdOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountAdOrder find = this.adHostAccountAdOrderDAO.find(id);
		if (find != null) {
			if(find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey())||find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.STOP.getKey())){
				throw new AdRuntimeException("订单生效中不能删除");
			}
			adHostAccountAdOrderDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderVo find(String id) {
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderVo> queryPager(AdHostAccountAdOrder condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrder> queryPage = adHostAccountAdOrderDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrder> result = queryPage.getRows();
		List<AdHostAccountAdOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderVo.class, result);
		AdPager<AdHostAccountAdOrderVo> resultVo = new AdPager<AdHostAccountAdOrderVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}

	@Override
	public void comfireOrder(String orderId) {
		if(StringUtils.isBlank(orderId)){
			throw new AdRuntimeException("无效记录");
		}
		AdHostAccountAdOrder adHostAccountAdOrder = this.adHostAccountAdOrderDAO.find(orderId);
		if(adHostAccountAdOrder==null){
			throw new AdRuntimeException("无效记录");
		}
		if(!adHostAccountAdOrder.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.UNSURE.getKey())){
			throw new AdRuntimeException("已经确认!");
		}
		AdHostAccount adHostAccount = this.adHostAccountDAO.find(adHostAccountAdOrder.getAccountId());
		if(adHostAccount==null){
			throw new AdRuntimeException("站长账号不存在");
		}
		if(adHostAccount.getAccountAmount().compareTo(BigDecimal.ONE)<1){
			throw new AdRuntimeException("账号余额不足");
		}
		if(adHostAccountAdOrder.getPayType().equals(EnumAdHostAccountAdOrderPayType.SETTLE_PREPAID)){
			AdHostAccountAdOrderAttri condition = new AdHostAccountAdOrderAttri();
			condition.setAdOrderId(adHostAccountAdOrder.getAdOrderId());
			List<AdHostAccountAdOrderAttri> query = (List<AdHostAccountAdOrderAttri>) this.attriDAO.query(condition);
			AdHostAccountAdOrderAttri orderAttri = query.get(0);
			BigDecimal adAmount = orderAttri.getAdAmount();
			if(adAmount.compareTo(adHostAccount.getAccountAmount())<0){
				throw new AdRuntimeException("预付费,账号余额不足");
			}
			adHostAccount.setFrozenAmount(adHostAccount.getFrozenAmount().add(adAmount));
			adHostAccount.setAccountAmount(adHostAccount.getAccountAmount().subtract(adAmount));
			UpdateByCondition<AdHostAccount> conditionHql = new UpdateByCondition<>(adHostAccount,true);
			int i = this.adHostAccountDAO.updateHql(conditionHql);
			if(i<1){
				throw new AdRuntimeException("多人操作账号,请重新尝试");
			}
		}
		adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey());
		UpdateByCondition<AdHostAccountAdOrder> conditionHql = new UpdateByCondition<AdHostAccountAdOrder>(adHostAccountAdOrder,true);
		int i = adHostAccountAdOrderDAO.updateHql(conditionHql);
		if(i<1){
			throw new AdRuntimeException("多人操作订间，请稍后重试");
		}
	}

	@Override
	public void stopOrder(String orderId) {
		if(StringUtils.isBlank(orderId)){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(orderId);
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		if(!find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey())){
			throw new AdRuntimeException("只有生效中的订单才能执行停状态");
		}
		find.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.STOP.getKey());
		UpdateByCondition<AdHostAccountAdOrder> condition = new UpdateByCondition<>(find,true);
		int i = adHostAccountAdOrderDAO.updateHql(condition);
		if(i<0){
			throw new AdRuntimeException("多人操作此订单");
		}
	}
	@Override
	public void invalidOrder(String orderId) {
		if(StringUtils.isBlank(orderId)){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(orderId);
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		if(!(find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey())||find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.STOP.getKey()))){
			throw new AdRuntimeException("订单款未确认或者已完成");
		}
		find.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.INVALID.getKey());
		if(find.getPayType().equals(EnumAdHostAccountAdOrderPayType.SETTLE_PREPAID.getKey())) { //如果预付费，冻结金额减少，账号金额增加
			String accountId = find.getAccountId();
			BigDecimal adHostAmount = find.getAdHostAmount();
			AdHostAccountAdOrderAttri condition  = new AdHostAccountAdOrderAttri();
			condition.setAdOrderId(find.getAdOrderId());
			List<AdHostAccountAdOrderAttri> queryList = (List<AdHostAccountAdOrderAttri>) attriDAO.query(condition);
			AdHostAccountAdOrderAttri orderAttri = queryList.get(0);
			BigDecimal adAmount = orderAttri.getAdAmount();
			BigDecimal blance = adAmount.subtract(adHostAmount);
			if(blance.compareTo(BigDecimal.ZERO)<0){
				blance = BigDecimal.ZERO;
			}
			AdHostAccount adHostAccount = this.adHostAccountDAO.find(accountId);
			adHostAccount.setAccountAmount(adHostAccount.getAccountAmount().add(blance));
			adHostAccount.setFrozenAmount(adHostAccount.getFrozenAmount().subtract(blance));
			UpdateByCondition<AdHostAccount> conditionHql = new UpdateByCondition<AdHostAccount>(adHostAccount,true);
			int i = this.adHostAccountDAO.updateHql(conditionHql);
			if(i<0){
				throw new AdRuntimeException("多人操作此账号");
			}
		}
		UpdateByCondition<AdHostAccountAdOrder> condition = new UpdateByCondition<>(find,true);
		int i = adHostAccountAdOrderDAO.updateHql(condition);
		if(i<0){
			throw new AdRuntimeException("多人操作此订单");
		}
	}

	@Override
	public void enable(String orderId) {
		if(StringUtils.isBlank(orderId)){
			throw new AdRuntimeException("无效记录");
		}
		AdHostAccountAdOrder adHostAccountAdOrder = this.adHostAccountAdOrderDAO.find(orderId);
		if(adHostAccountAdOrder==null){
			throw new AdRuntimeException("无效记录");
		}
		if(!adHostAccountAdOrder.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.STOP.getKey())){
			throw new AdRuntimeException("只有暂停的订单才能执行启用!");
		}
		AdHostAccount adHostAccount = this.adHostAccountDAO.find(adHostAccountAdOrder.getAccountId());
		if(adHostAccount==null){
			throw new AdRuntimeException("站长账号不存在");
		}
		if(!adHostAccountAdOrder.getPayType().equals(EnumAdHostAccountAdOrderPayType.SETTLE_PREPAID.getKey())){//如果非预付费，校验金额
			if(adHostAccount.getAccountAmount().compareTo(BigDecimal.ONE)<1){
				throw new AdRuntimeException("账号余额不足");
			}
		}
		adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey());
		UpdateByCondition<AdHostAccountAdOrder> conditionHql = new UpdateByCondition<>(adHostAccountAdOrder,true);
		int i = adHostAccountAdOrderDAO.updateHql(conditionHql);
		if(i<1){
			throw new AdRuntimeException("多人操作订间，请稍后重");
		}
	}

	@Override
	public void update(AdHostAccountAdOrder record,AdHostAccountAdOrderAttri attri) {
		if(record.getAdOrderId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(record.getAdOrderId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		if(find.getAdOrderStaus().equals(EnumAdHostAccountAdOrderStatus.WORKINIG.getKey())){
			throw new AdRuntimeException("订单生效中，不能修改");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderDAO.update(find);
		AdHostAccountAdOrderAttri condition = new AdHostAccountAdOrderAttri();
		condition.setAdOrderId(find.getAdOrderId());
		List<AdHostAccountAdOrderAttri> query = (List<AdHostAccountAdOrderAttri>) this.attriDAO.query(condition);
		if(query==null||query.size()<1){
			throw new AdRuntimeException("数据异常1");
		}
		AdHostAccountAdOrderAttri orderAttri = query.get(0);
		try {
			AdCommonsUtil.beanCopyWithoutNull(attri, orderAttri);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		this.attriDAO.update(orderAttri);
	}
}





