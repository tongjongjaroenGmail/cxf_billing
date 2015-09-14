package com.metasoft.billing.rest.model;

public class DhipResponse extends Response{
	private Float surInvest;// ค่าบริการ
	private Float surTrans;// ค่าพาหนะ
	private Float surDaily;// ค่าประจำวัน
	private Float surPhoto;// ค่ารูป
	private Float surClaim;// ค่าเรียกร้อง
	private Float surTel;// ค่าโทรศัพท์
	private Float surInsure;// ค่าประกันตัว
	private Float surOther;// ค่าใช้จ่ายอื่นๆ
	private Float total;// ยอดรวมก่อนภาษี
	private Float incVat;// ภาษี
	private Float totalAmount;// ยอดรวมหลังภาษี

	public Float getSurInvest() {
		return surInvest;
	}

	public void setSurInvest(Float surInvest) {
		this.surInvest = surInvest;
	}

	public Float getSurTrans() {
		return surTrans;
	}

	public void setSurTrans(Float surTrans) {
		this.surTrans = surTrans;
	}

	public Float getSurDaily() {
		return surDaily;
	}

	public void setSurDaily(Float surDaily) {
		this.surDaily = surDaily;
	}

	public Float getSurPhoto() {
		return surPhoto;
	}

	public void setSurPhoto(Float surPhoto) {
		this.surPhoto = surPhoto;
	}

	public Float getSurClaim() {
		return surClaim;
	}

	public void setSurClaim(Float surClaim) {
		this.surClaim = surClaim;
	}

	public Float getSurTel() {
		return surTel;
	}

	public void setSurTel(Float surTel) {
		this.surTel = surTel;
	}

	public Float getSurInsure() {
		return surInsure;
	}

	public void setSurInsure(Float surInsure) {
		this.surInsure = surInsure;
	}

	public Float getSurOther() {
		return surOther;
	}

	public void setSurOther(Float surOther) {
		this.surOther = surOther;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Float getIncVat() {
		return incVat;
	}

	public void setIncVat(Float incVat) {
		this.incVat = incVat;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

}
