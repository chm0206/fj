var domainName = "/";
name
function setCookie(name, value, minute) {
	// var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + minute * 60 * 1000);// 过期时间
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString() + ";path=" + domainName;
}
/**
 * 获取指定cookie的值
 * @param name
 * @returns
 */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
/**
 * 删除指定的cookie
 * @param name 
 */
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString()
				+ ";path=" + domainName;
}
/**
 * 获取accToken
 * @returns
 */
function getAccToken(){
	return getCookie(cookieName[0]);
}
var cookieName = ["accToken"];
function setAllCookie(accToken){
	setCookie(cookieName[0], accToken, 30);
}
/**
 * 清除所有cookie
 */
function clearCookie(){
	for(var ci = 0;ci<cookieName.length;ci++){
		delCookie(cookieName[ci]);
	}
}