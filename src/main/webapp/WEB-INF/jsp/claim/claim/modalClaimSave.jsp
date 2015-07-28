<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="modal fade" id="modalSave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="overflow-x: auto; overflow-y: auto;">
		<div class="modal-dialog" style="width: 1100px">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h5 class="modal-title" id="modalSaveHeaderLabel">เรื่องเรียกร้อง --> <span id="modalSaveHeaderLabelFunction"></span></h5>
				</div>
				<div class="modal-body" style="padding: 5px;">
					<input type="hidden" id="txtClaimId">
					
					<div class="well" style="padding: 5px;margin-bottom: 4px;">
					
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
										<select class="col-sm-12" id="selJobStatus" onchange="changeSelJobStatus(this.value);">
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
										<b>เลขเคลม * : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control require" id="txtClaimNumber" type="text" maxlength="20" title="เลขเคลม"/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขกรมธรรม์  * : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control require" id="txtPolicyNo" type="text" maxlength="20" title="เลขกรมธรรม์"/> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ทะเบียนรถ  * : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control require" id="txtlicenseNumber" type="text" maxlength="10" title="ทะเบียนรถ "/> 
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
										<b>วันที่เกิดเหตุ * : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control date-picker require" title="วันที่เกิดเหตุ" id="txtAccidentDate" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันครบอายุความ * : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control date-picker require" title="วันครบอายุความ" id="txtMaturityDate" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
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
										<select class="col-sm-12" id="selClaimType" title="ประเภทเคลม">
											<c:forEach var="claimType" items="${claimTypes}" varStatus="index">		
												<option value="${claimType.id}">${claimType.name}</option>					
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>					
					</div>	
					
					</div>
				
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
										<b>จำนวนเงินที่เรียกร้อง  : </b> 
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
					
					<div class="well" style="padding: 5px;margin-bottom: 4px;">
					
					<div class="row">
						<div class="col-sm-12">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>บริษัทประกัน * : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12 require" title="บริษัทประกัน" id="selPartyInsurance" onchange="changePartyInsurance(this.value);">
											<option value=""></option>		
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
											<input class="form-control" id="txtPartyLicenseNumber" type="text" maxlength="10"/> 
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
						<div class="col-sm-6">
							<div class="table-responsive">
								<div class="col-sm-4 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>เลขใบสำคัญจ่าย : </b> 
									</div>
								</div>
								<div class="col-sm-7 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<textarea class="form-control" id="txtInvoiceNumber" cols="50" rows="5"> 
										</textarea>
									</div>
								</div>
								
							</div>
						</div>	
						
						<div class="col-sm-6">
							<div class="table-responsive">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>หมายเหตุ : </b> 
									</div>
								</div>
								<div class="col-sm-9 no-padding-left">		
									
									<div class="tabbable">
										<ul class="nav nav-tabs" id="myTab3">
											<li class="active">
												<a data-toggle="tab" href="#jobStatusRemark1">
													รับงาน
												</a>
											</li>

											<li>
												<a data-toggle="tab" href="#jobStatusRemark2">
													ออกหนังสือติดตาม
												</a>
											</li>

											<li>
												<a data-toggle="tab" href="#jobStatusRemark3">
													ปิดงาน
												</a>
											</li>
											
											<li>
												<a data-toggle="tab" href="#jobStatusRemark4">
													ยกเลิก
												</a>
											</li>
										</ul>

										<div class="tab-content">
											<div id="jobStatusRemark1" class="tab-pane in active">
												<textarea class="form-control" id="txtReceiveRemark" cols="50" rows="5"></textarea>
											</div>

											<div id="jobStatusRemark2" class="tab-pane">
												<textarea class="form-control" id="txtFollowRemark" cols="50" rows="5"></textarea>
											</div>

											<div id="jobStatusRemark3" class="tab-pane">
												<textarea class="form-control" id="txtCloseRemark" cols="50" rows="5"></textarea>
											</div>
											
											<div id="jobStatusRemark4" class="tab-pane">
												<textarea class="form-control" id="txtCancelRemark" cols="50" rows="5"></textarea>
											</div>
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
										<b>ผู้รับผิดชอบ : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<select class="col-sm-12" id="selAgent">
											<option value=""></option>
										</select>
									</div>
								</div>	
								<div id="divReceiveMoneyType" style="display: none;">
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>ประเภทการรับเงิน : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" >
										<select class="col-sm-12" id="selReceiveMoneyType" title="ประเภทการรับเงิน">
											<option value=""></option>			
											<c:forEach var="receiveMoneyType" items="${receiveMoneyTypes}" varStatus="index">		
												<option value="${receiveMoneyType.id}">${receiveMoneyType.name}</option>					
											</c:forEach>
										</select>
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
										<b>วันที่ออกหนังสือติดตาม : </b> 
									</div>
								</div>
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtFollowDate" type="text" readonly="readonly"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันที่ปิดงาน : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtCloseDate" type="text" readonly="readonly"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
								
								<div class="col-sm-1 no-padding-left">		
									<div class="input-group col-sm-12 no-padding-left no-padding-right" style="text-align: right;">
										<b>วันที่ยกเลิก : </b> 
									</div>
								</div>
								
								<div class="col-sm-2 no-padding-left">	
									<div class="input-group col-sm-12 no-padding-left no-padding-right">
										<input class="form-control" id="txtCancelDate" type="text" readonly="readonly"/> 
										<span class="input-group-addon"> 
											<i class="icon-calendar bigger-110"></i>
										</span>
									</div>
								</div>
							</div>
						</div>		
					</div>
				
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-success" id="btnSave" onclick="save();">บันทึก</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">ปิด</button>
				</div>
			</div>
		</div>
	</div>