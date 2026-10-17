/**
 * 功能:
 * 1.全部查詢
 * 2.查詢單筆
 * 3.顯示訊息
 * 4.顯示表格
 * 
 */

// Web API 路徑
const API_BASE_URL = "http://localhost:8080/book";

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

// 取得其他畫面元素
const messageBox = document.getElementById('messageBox');
const singleResult = document.getElementById('singleResult');
const searchId = document.getElementById('searchId');
const findOneBtn = document.getElementById('findOneBtn');

// 紀錄目前表單模式
// create = 新增模式(預設)
// update = 修改模式
let formMode = "create";

// 監聽按鈕事件
addBtn.addEventListener('click', addBook);
findOneBtn.addEventListener('click', () => findOneBook(searchId.value));
updateBtn.addEventListener('click', updateBook);

// 頁面載入初始化
window.addEventListener('DOMContentLoaded', () => {
	
	console.log('網頁載入成功 !');
	
	// 查詢所有書籍
	findAllBooks();	
});

// 顯示訊息
function showMessage(message, type = "info") {
	messageBox.textContent = message;
	messageBox.className = `message-box ${type}`;
}

// 新增書籍
async function addBook() {
	console.log('按下[新增書籍]');
	try {
		
		const book = {
			name: bookName.value.trim(),
			price: bookPrice.value ? Number(bookPrice.value) : null,
			amount: bookAmount.value ? Number(bookAmount.value) : null,
			pub: bookPub.checked
		}; 
		
		console.log('Add Book:', book);
		
		// 檢查資料是否有內容
		if(!book.name || book.price == null || book.amount == null) {
			showMessage('請輸入完整書名,價格與數量', 'error');
			return;
		}
		
		// 透過 Web API 新增書籍
		// 得到回應
		const response = await fetch(API_BASE_URL, {
			method: 'POST',
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(book)
		});
		console.log('Add Book response:', response);
		
		// 取得結果
		const result = await handleResponse(response);
		console.log('Add Book result:', result);
		
		// 清空表單
		bookForm.reset();
		
		// 重新查詢所有書籍
		findAllBooks();
		
	} catch(e) {
		console.log('新增書籍失敗:', e);
		showMessage("新增書籍失敗:" + e, "error");
	}
	
}

// 修改書籍
async function updateBook() {
	try {
		
		// 建立要修改的書籍 book 物件(json格式)
		const book = {
			id: bookId.value ? Number(bookId.value) : null,
			name: bookName.value.trim(),
			price: bookPrice.value ? Number(bookPrice.value) : null,
			amount: bookAmount.value ? Number(bookAmount.value) : null,
			pub: bookPub.checked
		};
		
		// 得到回應
		const response = await fetch(`${API_BASE_URL}/${book.id}`, {
			method: "PUT",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(book)
		});
		
		// 得到結果
		const result = await handleResponse(response);
		showMessage(result.message || "修改書籍成功", "success");
		
		// 重新查詢
		findAllBooks();
		
	} catch(e) {
		console.log('修改書籍失敗:', e);
		showMessage("修改書籍失敗:" + e, "error");
	}
	
}

// 查詢單筆
async function findOneBook(id) {
	
	try {
		// 透過 Web API 查詢書籍
		// 得到回應
		// 假設 id=1
		// `${API_BASE_URL}/${id}` => http://localhost:8080/book/1
		const response = await fetch(`${API_BASE_URL}/${id}`);
		// 取得結果
		const result = await handleResponse(response);
		console.log('result:', result, JSON.stringify(result));
		singleResult.innerHTML = JSON.stringify(result.data);
		
		// 顯示結果
		showMessage(result.message || "單筆查詢成功", "success");
		
		// 將資料放入到表單中
		fillForm(result.data);
		
	} catch(e) {
		console.log('err:', e);
		singleResult.innerHTML = e;
		showMessage("單筆查詢失敗", "error");
	}
	
}

// 查詢所有書籍
async function findAllBooks() {
	
	try {
		// 透過 Web API 查詢書籍
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

// 將 json 資料放入到表單欄位
function fillForm(book) {
	bookId.value = book.id ?? "";
	bookName.value = book.name ?? "";
	bookPrice.value = book.price ?? "";
	bookAmount.value = book.amount ?? "";
	// 單選盒
	bookPub.checked = book.pub ?? false;
	
}

// 統一處理 fetch 回應
async function handleResponse(response) {
	const result = await response.json();
	
	if(!response.ok) {
		throw new Error(result.message || '發生錯誤');
	}
	
	return result;
}




