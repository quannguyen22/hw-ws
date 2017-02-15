package com.viettel.ocs.helper;

import java.util.Set;
import java.util.SortedSet;

import com.google.common.collect.Sets;
import com.viettel.ocs.util.Constant;
import com.viettel.ocs.util.FileUtil;

public class CompareStepOneAndStepTwoHelper {

	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_batch
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpBatch (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_BATCH).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_BATCH).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_acm_m
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpAcmM (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_ACM_M).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_ACM_M).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_subs_upp
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpSubsUpp (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_SUBS_UPP).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_SUBS_UPP).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_vt_cut
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpVtCut (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_VT_CUT).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_VT_CUT).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_fn_nbr
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpFnNbr (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_FN_NBR).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_FN_NBR).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_ocs_cdr_his
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpOcsCdrHis (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_OCS_CDR_HIS).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_OCS_CDR_HIS).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_fn_srv
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpFnSrv (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_FN_SRV).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_FN_SRV).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_blacklist_subs
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpBlacklistSubs (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_BLACKLIST_SUBS).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_BLACKLIST_SUBS).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_recharge_24h
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpRecharge24h (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_RECHARGE_24H).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_RECHARGE_24H).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_lang_en
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpLangEn (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_LANG_EN).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_LANG_EN).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
	
	/**
	 * Lay Set cac thong tin giong nhau cua 2 Set thong tin tmp_vt_cut_sea
	 * 
	 * @param msisdnStepOne
	 * @param msisdnStepTwo
	 * @return
	 */
	public static Set<String> compareTmpVtCutSea (){
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnStepOne = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_1.concat("/").concat(Constant.TMP_VT_CUT_SEA).concat(".txt"));
		SortedSet<String> msisdnStepTwo = fileUtil.getAllMsisdnInfo(Constant.PATH_STEP_2.concat("/").concat(Constant.TMP_VT_CUT_SEA).concat(".txt"));
		
		return Sets.intersection(msisdnStepOne, msisdnStepTwo);
	}
}
