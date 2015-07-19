/**
 * 
 */
package com.metasoft.claim.service.claim;

import java.util.List;

import com.metasoft.claim.bean.UploadedFile;
import com.metasoft.claim.controller.vo.ImportError;
import com.metasoft.claim.dao.claim.ClaimDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.model.TblClaimRecovery;
import com.metasoft.claim.service.ModelBasedService;

public interface ClaimImportService extends ModelBasedService<ClaimDao, TblClaimRecovery, Integer> {
	public List<ImportError> saveFromFile(UploadedFile uploadedFile,SecUser user)  throws Exception;
}
