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
		
		renderBookTable(result);
	} catch(e) {
		console.log('err:', e);
	}
	
} 

// 渲染表格(給全部書籍列表使用)
function renderBookTable(result) {
	const books = result.data;
	console.log('books:', books);
}


// 統一處理 fetch 回應
async function handleResponse(response) {
	const result = await response.json();
	
	if(!response.ok) {
		throw new Error(result.message || '發生錯誤');
	}
	
	return result;
}




