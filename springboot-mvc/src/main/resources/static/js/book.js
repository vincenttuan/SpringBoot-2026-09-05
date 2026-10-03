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

// 取得表單元素
const bookForm   = document.getElementById('bookForm');
const bookId     = document.getElementById('bookId'); 
const bookName   = document.getElementById('bookName'); 
const bookPrice  = document.getElementById('bookPrice'); 
const bookAmount = document.getElementById('bookAmount'); 
const bookPub    = document.getElementById('bookPub'); 
const addBtn     = document.getElementById('addBtn'); 
const updateBtn  = document.getElementById('updateBtn'); 
const resetBtn   = document.getElementById('resetBtn'); 


// 頁面載入初始化
window.addEventListener('DOMContentLoaded', () => {
	
	console.log('網頁載入成功 !');
	
	// 查詢所有書籍
	findAllBooks();	
});

// 查詢所有書籍
async function findAllBooks() {
	
	try {
		// 得到回應
		const response = await fetch(API_BASE_URL);
		console.log('response:', response);
		
		// 取得結果
		const result = await handleResponse(response);
		console.log('result:', result);
		
		// 資料渲染
		renderBookTable(result);
	} catch(e) {
		console.log('err:', e);
	}
	
} 

// 渲染表格(給全部書籍列表使用)
function renderBookTable(result) {
	const books = result.data;
	console.log('books:', books);
	
	let html = "";
	
	books.forEach(book => {
		console.log('book:', book);
		
		html += `
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.amount}</td>
				<td>${book.pub}</td>
				<td></td>
			</tr>
		`;
		
	});
	
	bookTableBody.innerHTML = html;
}


// 統一處理 fetch 回應
async function handleResponse(response) {
	const result = await response.json();
	
	if(!response.ok) {
		throw new Error(result.message || '發生錯誤');
	}
	
	return result;
}




