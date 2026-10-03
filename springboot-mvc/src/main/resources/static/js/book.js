/**
 * 功能:
 * 1.全部查詢
 * 2.查詢單筆
 * 3.顯示訊息
 * 4.顯示表格
 * 
 */

// Web API 路徑
const API_BASE_URL = "/book";

// 頁面載入初始化
window.addEventListener('DOMContentLoaded', () => {
	
	console.log('網頁載入成功 !');
	
	// 查詢所有書籍
	findAllBooks();	
});

// 查詢所有書籍
async function findAllBooks() {
	
	try {
		const response = await fetch(API_BASE_URL);
		console.log('response:', response);
		const result = await handleResponse(response);
		console.log('result:', result);
		
	} catch(e) {
		console.log('err:', e);
	}
	
} 

// 統一處理 fetch 回應
async function handleResponse(response) {
	const result = await response.json();
	if(!result.ok) {
		throw new Error(response.message || '發生錯誤');
	}
	return result;
}




