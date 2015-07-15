<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="modal fade" id="modalSave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="overflow-x: hidden; overflow-y: auto;">
		<div class="modal-dialog" style="width: 1100px">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="modalSaveHeaderLabel">เรื่องเรียกร้อง --> <span id="modalSaveHeaderLabelFunction"></span></h5>
				</div>
				<div class="modal-body">
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขเรียกร้อง : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtJobNo" type="text" maxlength="20" readonly="readonly"/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันที่รับงาน : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtJobDate" type="text" readonly="readonly"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-1 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>สถานะ : </b> 
									</div>
								</div>
								
								<div class="col-sm-3 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12" id="selJobStatus">
											<c:forEach var="jobStatus" items="${jobStatuses}" varStatus="index">		
												<option value="${jobStatus.id}">${jobStatus.name}</option>					
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>		
					</div>
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขเคลม : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtClaimNumber" type="text" maxlength="20"/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขกรมธรรม์ : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtPolicyNo" type="text" maxlength="20"/> 
									</div>
								</div>
								
								<div class="col-sm-1 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ทะเบียนรถ : </b> 
									</div>
								</div>
								
								<div class="col-sm-3 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtlicenseNumber" type="text" maxlength="10"/> 
									</div>
								</div>
							</div>
						</div>		
					</div>		
							
					<div class="space-4"></div>
				
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันที่เกิดเหตุ: </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control date-picker" id="txtAccidentDate" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันครบอายุความ : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control date-picker" id="txtMaturityDate" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ประเภทเคลม : </b> 
									</div>
								</div>
								
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12" id="selClaimType">
											<c:forEach var="claimType" items="${claimTypes}" varStatus="index">		
												<option value="${claimType.id}">${claimType.name}</option>					
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>					
					</div>	
					
					<div class="space-4"></div>
				
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>จำนวนเงินทิพยเรียกร้อง: </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtClaimInsuranceAmount" type="text" maxlength="14" style="text-align: right;"
											onkeypress="return inputReal(this,2,event);"
											onfocus="this.value = delCommas(this.value);" 
											onblur="this.value = addCommas(this.value);"
										/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>จำนวนเงินที่เรียกร้อง : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtRequestAmount" type="text" maxlength="14" style="text-align: right;"
											onkeypress="return inputReal(this,2,event);"
											onfocus="this.value = delCommas(this.value);" 
											onblur="this.value = addCommas(this.value);"
										/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>จำนวนเงินที่เรียกร้องได้ : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="" id="txtClaimAmount" type="text" maxlength="14" style="text-align: right;"
											onkeypress="return inputReal(this,2,event);"
											onfocus="this.value = delCommas(this.value);" 
											onblur="this.value = addCommas(this.value);"
										/> 
									</div>
								</div>
							</div>
						</div>		
					</div>	
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>บริษัทประกัน : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12" id="selPartyInsurance">
											<c:forEach var="insurance" items="${insurances}" varStatus="index">		
												<option value="${insurance.id}">${insurance.name}</option>					
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ทะเบียนรถคู่กรณี : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<div class="input-group col-sm-12 no-padding-left no-padding-right">
											<input class="form-control" id="txtPartylicenseNumber" type="text" maxlength="10"/> 
										</div>
									</div>
								</div>
							</div>
						</div>		
					</div>	
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขเคลมคู่กรณี : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtPartyClaimNumber" type="text" maxlength="20"/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขกรมธรรม์คู่กรณี : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtPartyPolicyNo" type="text" maxlength="20"/>
									</div>
								</div>
							</div>
						</div>		
					</div>	
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขใบสำคัญจ่าย : </b> 
									</div>
								</div>
								<div class="col-sm-4 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<textarea class="form-control" id="txtInvoiceNumber" cols="50" rows="5"> 
										</textarea>
									</div>
								</div>
								
							</div>
						</div>		
					</div>	
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>หมายเหตุ : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									
								</div>	
							</div>
						</div>		
					</div>	
					
					<div class="space-4"></div>
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ผู้รับผิดชอบ : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12" id="selPartyInsurance">
											<c:forEach var="agent" items="${agents}" varStatus="index">		
												<option value="${agent.id}">${agent.name}&nbsp;&nbsp;${agent.lastName}</option>					
											</c:forEach>
										</select>
									</div>
								</div>	
							</div>
						</div>		
					</div>	
				
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">ปิด</button>
				</div>
			</div>
		</div>
	</div>