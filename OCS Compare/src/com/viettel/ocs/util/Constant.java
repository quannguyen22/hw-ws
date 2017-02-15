/*
 * Created on Feb 13, 2017
 *
 * Copyright (C) 2017 by Viettel Network Company. All rights reserved
 */
package com.viettel.ocs.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Cac tham so config tuong ung voi file config.properties
 * 
 * @author Nguyen Hai Ha (hanh45@viettel.com.vn)
 * @since Feb 13, 2017
 * @version 1.0.0
 */
public class Constant {

	public static String STEP = "1"; // Chay buoc 1 step = 1; Chay buoc 2 step = 2;
	
	/**
	 * Thong tin duong dan luu file cua buoc 1 va buoc 2
	 */
	public static final String PATH_STEP_1 = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.info.1.path"));
	public static final String PATH_STEP_2 = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.info.2.path"));
	
	/**
	 * Thong tin cua 9 webservice dung truy van thong tin thue bao
	 */
	public static final String QUERY_ACCUMULATION_USAGE = "query.accumulation.usage";
	public static final String QUERY_BALANCE = "query.balance";
	public static final String QUERY_CUSTOMER_INFO = "query.customer.info";
	public static final String QUERY_FREE_UNIT = "query.free.unit";
	public static final String QUERY_LOAN_LOG = "query.loan.log";
	public static final String QUERY_OFFERING_INST_PROPERTY = "query.offering.inst.property";
	public static final String QUERY_RECHARGE_LOG = "query.recharge.log";
	public static final String QUERY_SUB_LIFECYCLE = "query.sub.lifecycle";
	public static final String QUERY_SUB_STATUS_HIS = "query.sub.status.his";

	// File name
	public static final String TMP_BATCH = "tmp_batch";
	public static final String TMP_ACM_M = "tmp_acm_m";
	public static final String TMP_SUBS_UPP = "tmp_subs_upp";
	public static final String TMP_VT_CUT = "tmp_vt_cut";
	public static final String TMP_FN_NBR = "tmp_fn_nbr";
	public static final String TMP_OCS_CDR_HIS = "tmp_ocs_cdr_his";
	public static final String TMP_FN_SRV = "tmp_fn_srv";
	public static final String TMP_BLACKLIST_SUBS = "tmp_blacklist_subs";
	public static final String TMP_RECHARGE_24H = "tmp_recharge_24h";
	public static final String TMP_LANG_EN = "tmp_lang_en";
	public static final String TMP_VT_CUT_SEA = "tmp_vt_cut_sea";
	
	// File name result by intersection step 1 and step 2
	public static final String VIETTEL_TMP_BATCH = "viettel_tmp_batch";
	public static final String VIETTEL_TMP_ACM_M = "viettel_tmp_acm_m";
	public static final String VIETTEL_TMP_SUBS_UPP = "viettel_tmp_subs_upp";
	public static final String VIETTEL_TMP_VT_CUT = "viettel_tmp_vt_cut";
	public static final String VIETTEL_TMP_FN_NBR = "viettel_tmp_fn_nbr";
	public static final String VIETTEL_TMP_OCS_CDR_HIS = "viettel_tmp_ocs_cdr_his";
	public static final String VIETTEL_TMP_FN_SRV = "viettel_tmp_fn_srv";
	public static final String VIETTEL_TMP_BLACKLIST_SUBS = "viettel_tmp_blacklist_subs";
	public static final String VIETTEL_TMP_RECHARGE_24H = "viettel_tmp_recharge_24h";
	public static final String VIETTEL_TMP_LANG_EN = "viettel_tmp_lang_en";
	public static final String VIETTEL_TMP_VT_CUT_SEA = "viettel_tmp_vt_cut_sea";
	
	// Thong tin ten file cua huawei
	public static final String HUAWEI_TMP_BATCH = "TMP_BATCH";
	public static final String HUAWEI_TMP_ACM_M = "TMP_ACM_M";
	public static final String HUAWEI_TMP_SUBS_UPP = "TMP_SUBS_UPP";
	public static final String HUAWEI_TMP_VT_CUT = "TMP_VT_CUT";
	public static final String HUAWEI_TMP_FN_NBR = "TMP_FN_NBR";
	public static final String HUAWEI_TMP_OCS_CDR_HIS = "TMP_OCS_CDR_HIS";
	public static final String HUAWEI_TMP_FN_SRV = "TMP_FN_SRV";
	public static final String HUAWEI_TMP_BLACKLIST_SUBS = "TMP_BLACKLIST_SUBS";
	public static final String HUAWEI_TMP_RECHARGE_24H = "TMP_RECHARGE_24H";
	public static final String HUAWEI_TMP_LANG_EN = "TMP_LANG_EN";
	public static final String HUAWEI_TMP_VT_CUT_SEA = "TMP_VT_CUT_SEA";
	
	
}
