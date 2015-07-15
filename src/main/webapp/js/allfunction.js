function addSlashes(input) {
	var v = input.value;
	if (v.match(/^\d{2}$/) !== null) {
		input.value = v + '/';
	} else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
		input.value = v + '/';
	}
}
function LTrim(str)
/*
 * PURPOSE: Remove leading blanks from our string. IN: str - the string we want
 * to LTrim
 */
{
	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		// We have a string with leading blank(s)...

		var j = 0, i = s.length;

		// Iterate from the far left of string until we
		// don't have any more whitespace...
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
			j++;

		// Get the substring from the first non-whitespace
		// character to the end of the string...
		s = s.substring(j, i);
	}
	return s;
}
function RTrim(str)
/*
 * PURPOSE: Remove trailing blanks from our string. IN: str - the string we want
 * to RTrim
 * 
 */
{
	// We don't want to trip JUST spaces, but also tabs,
	// line feeds, etc. Add anything else you want to
	// "trim" here in Whitespace
	var whitespace = new String(" \t\n\r");

	var s = new String(str);

	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		// We have a string with trailing blank(s)...

		var i = s.length - 1; // Get length of string

		// Iterate from the far right of string until we
		// don't have any more whitespace...
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
			i--;

		// Get the substring from the front of the string to
		// where the last non-whitespace character is...
		s = s.substring(0, i + 1);
	}

	return s;
}

function Trim(str)
/*
 * PURPOSE: Remove trailing and leading blanks from our string. IN: str - the
 * string we want to Trim
 * 
 * RETVAL: A Trimmed string!
 */
{
	return RTrim(LTrim(str));
}
function numbersonly() {
	if (event.keyCode < 48 || event.keyCode > 57)
		return false;
}
function numbersonlyRange(evt,_this,start,end) {
	var keyID = (evt.charCode) ? evt.charCode : ((evt.which) ? evt.which : evt.keyCode);
	if((keyID>=37 && keyID<=40) || keyID==8){
		return true;
	}
	if (!(keyID >= 48 && keyID <= 57 )) { //|| keyID >= 97 && keyID <= 106
		return false;
	}
	
	if(_this.selectionStart != _this.selectionEnd){
		return true;
	}
	
	var num = parseInt(_this.value+String.fromCharCode(keyID));
	if (num >= start && num <= end){
		return true;
	}
	return false;
}
function blurNumbersonlyRange(_this,start,end) {
	var num = parseInt(_this.value);
	if (!(num >= start && num <= end)){
		_this.value = "";
	}
}
function numbersonly1() {
	if (event.keyCode == 46)
		return true;
	else if (event.keyCode < 48 || event.keyCode > 57)
		return false;
}

function ReplaceAll(inText, inFindStr, inReplStr, inCaseSensitive) {
	var searchFrom = 0;
	var offset = 0;
	var outText = "";
	var searchText = "";
	if (inCaseSensitive == null) {
		inCaseSensitive = false;
	}
	if (inCaseSensitive) {
		searchText = inText.toLowerCase();
		inFindStr = inFindStr.toLowerCase();
	} else {
		searchText = inText;
	}
	offset = searchText.indexOf(inFindStr, searchFrom);
	while (offset != -1) {
		outText += inText.substring(searchFrom, offset);
		outText += inReplStr;
		searchFrom = offset + inFindStr.length;
		offset = searchText.indexOf(inFindStr, searchFrom);
	}
	outText += inText.substring(searchFrom, inText.length);

	return (outText);
}
function replaceurl(inText) {
	inText = ReplaceAll(inText, '%', '%25');
	inText = ReplaceAll(inText, '&', '%26');
	inText = ReplaceAll(inText, '<', '%3C');
	inText = ReplaceAll(inText, '>', '%3E');
	inText = ReplaceAll(inText, '=', '%3D');
	inText = ReplaceAll(inText, '+', '%2B');
	inText = ReplaceAll(inText, '#', '%23');
	inText = ReplaceAll(inText, '?', '%40');
	outText = inText;
	return (outText);
}
function checkSelect(chk1,chk2)
{
	for(var i=0;i<chk2.length;i++){
		if(chk2[i].disabled==true) {
			chk2[i].checked = false;
		}else {
			chk2[i].checked = chk1.checked;
		}
		
	}
	return;
}

// time format ==> hh:mm
function inputTime(e, objText){
	var keynum = e.keyCode;
	if(inputNum(e)){
		if((objText.value.length==2 && keynum!=8) || keynum==37 || keynum==39){
			objText.value += ':';
		}
		return true;
	}else{
		return false;
	}
}

function inputNum(e){
	var keynum;
	if(window.event){// IE
		keynum = e.keyCode;
	}else if(e.which){// NC//FF//OP
		keynum = e.which;
	}
	if(!keynum){return true;}
	if(keynum==45){return true;}
	if(keynum==8){return true;}
	if(keynum==9){return true;}
	if ((keynum>=48 && keynum<=57) || keynum==37 || keynum==39){
    	return true;
	}else{
	    return false;
	}
}


function isNum0to9andpoint(objText, e){// get number and point
	var keynum;
	if(window.event){// IE
		keynum = e.keyCode;
	}else if(e.which){// NC//FF//OP
		keynum = e.which;
	}
// alert(keynum);
	if(!keynum){return true;}
	if(keynum==45){return true;}
	if(keynum==8 || keynum==9){return true;}
  	if ((keynum>=48 && keynum<=57) || keynum==46 || keynum==190 || keynum==110 || keynum==37 || keynum==39){
  		if(keynum==190 || keynum==110){
  			// check point(.)
  			if(objText.value.indexOf('.')>0){
  				return false;
  			}
  		}
		return true;
	 }else{
	    return false;
	 }
}

function isNum0to9andpoint2(objText, keynum){// get number and point
// alert(keynum);
	if(!keynum){return true;}
	if(keynum==45){return true;}
	if(keynum==8 || keynum==9){return true;}
  	if ((keynum>=48 && keynum<=57) || keynum==46 || keynum==190 || keynum==110 || keynum==37 || keynum==39){
  		if(keynum==190 || keynum==110){
  			// check point(.)
  			if(objText.value.indexOf('.')>0){
  				return false;
  			}
  		}
		return true;
	 }else{
	    return false;
	 }
}


function inputReal(objText,point,e){
	var keynum;
	if(window.event){// IE
		keynum = e.keyCode;
	}else if(e.which){// NC//FF//OP
		keynum = e.which;
	}
	var pos = objText.selectionStart;
	var cursorpos;
	if(pos==0){
		cursorpos = pos;
	}else{
		if(!pos){// IE
			cursorpos = getCursorPos(objText);
		}else{// NC,FF,OP
			cursorpos = pos;
		}
	}
	if(keynum){
		if(keynum==9){return true;}
		if(keynum==45){return true;}
		if(keynum!=8){
			if(keynum!=189){
				if(isNum0to9andpoint2(objText,keynum)==false){
					return false;
				}
			}
			var num = objText.value;
//			if(num.indexOf('.')>8){
//				if(num.length>11){return false;}
//			}
			if(keynum!=46){
				if((num.indexOf('.')==-1)&&(num.length==14)){return false;}
			}else{
				if(num.length-cursorpos>2){return false;}
			}
			if((num.indexOf('.')!=-1)&&(keynum==46)){
				return false;
			}
			if((num.indexOf('.')!=-1)){
				var x = num.substring(num.indexOf('.')+1,num.length);
				if(x.length>(point-1)){// check size of number of back point
					if(cursorpos>num.indexOf('.')){
						return false;
					}		
				}
			}
		}
	}
	return true;
}

function getCursorPos(textElement) {
// create a range object and save off it's text
	var objRange = document.selection.createRange();
	var sOldRange = objRange.text;
 
// set this string to a small string that will not normally be encountered
	var sWeirdString = '#%~';
 
// insert the weirdstring where the cursor is at
	objRange.text = sOldRange + sWeirdString; 
	objRange.moveStart('character', (0 - sOldRange.length - sWeirdString.length));

// save off the new string with the weirdstring in it
	var sNewText = textElement.value;

// set the actual text value back to how it was
	objRange.text = sOldRange;

// look through the new string we saved off and find the location of
// the weirdstring that was inserted and return that value
	for (i=0; i <= sNewText.length; i++) {
		var sTemp = sNewText.substring(i, i + sWeirdString.length);
		if (sTemp == sWeirdString) {
			var cursorPos = (i - sOldRange.length);
			return cursorPos;
		}
	}
}

function addCommas(nStr) {
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function delCommas(nStr){
	var i, x, x2;
	var x3 = '';

	nStr += '';
	x = nStr.split(',');
	x2 = x.length;
	for(i=0; i<x2; i++){
		x3 += x[i];
	}

	return x3;
}

// -------- check telephone number -----------
var digits = "0123456789";
var phoneNumberDelimiters = "()- ";
var validWorldPhoneChars = phoneNumberDelimiters + "+";
var minDigitsInIPhoneNumber = 9;

function isInteger(s){   
	var i;
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    return true;
}

function trim(s){  
	var i;
    var returnString = "";
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (c != " ") returnString += c;
    }
    return returnString;
}

function stripCharsInBag(s, bag)
{   
	var i;
    var returnString = "";
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function checkInternationalPhone(strPhone){
	// allow '-' in phone & mobile field.
	if(strPhone=='-'){
		return true;
	}
	var bracket=3;
	strPhone=trim(strPhone);
	if(strPhone.indexOf("+")>1) return false;
	if(strPhone.indexOf("-")!=-1)bracket=bracket+1;
	if(strPhone.indexOf("(")!=-1 && strPhone.indexOf("(")>bracket)return false;
	var brchr=strPhone.indexOf("(");
	if(strPhone.indexOf("(")!=-1 && strPhone.charAt(brchr+2)!=")")return false;
	if(strPhone.indexOf("(")==-1 && strPhone.indexOf(")")!=-1)return false;
	s=stripCharsInBag(strPhone,validWorldPhoneChars);
	return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}

// check number with jQuery.
function checkNum(objText){
	var newNum = parseInt(objText.val());
	if(isNaN(newNum)){
		return false;
	}else{return true;}
}

function datedifference(objDateFrom, objDateTo){
	var DateFrom = objDateFrom.value;
	var DateTo = objDateTo.value;
	if(DateFrom=='' || DateTo==''){return true;}
	DateFrom = DateFrom.split("/");
	starttime = new Date(DateFrom[2],DateFrom[1]-1,DateFrom[0]);

	DateTo = DateTo.split("/");
	endtime = new Date(DateTo[2],DateTo[1]-1,DateTo[0]);
	if((endtime-starttime) < 0){
		return false;
	}else{
		return true;
	}
}

String.prototype.startsWith = function(str) 
{return (this.match("^"+str)==str);};

// for key 0-9
function chkNumber0to9(e) {
	var unicode = e.charCode? e.charCode : e.keyCode;
	var mozilla = document.getElementById && !document.all;
	if (mozilla){
		if (e.keyCode.toString().search(/^(8|9|45|46|35|36|37|38|39|40)$/) != (-1))
		{return true;}
	}
	var numPattern = /^\d*[0-9]?$/;
	var chk = numPattern.test(String.fromCharCode(unicode));
	if (!chk){
		return false;
		// Event.stop(e);
	}
	return true;
}

function setZero(obj){
	var txtZero = "";
	var txtZeroLength = 5 - obj.value.length;
	for(var j = 0;j < txtZeroLength; j++){
		txtZero += "0";
	}
	return txtZero + obj.value;
}