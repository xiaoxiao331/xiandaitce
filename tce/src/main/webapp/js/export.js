// 将一个sheet转成最终的excel文件的blob对象，然后利用URL.createObjectURL下载
function sheet2blob(sheet, sheetName) {
    sheetName = sheetName || 'sheet1';
    var workbook = {
        SheetNames: [sheetName],
        Sheets: {}
    };
    workbook.Sheets[sheetName] = sheet; // 生成excel的配置项

    var wopts = {
        bookType: 'xlsx', // 要生成的文件类型
        bookSST: false, // 是否生成Shared String Table，官方解释是，如果开启生成速度会下降，但在低版本IOS设备上有更好的兼容性
        type: 'binary'
    };
    var wbout = XLSX.write(workbook, wopts);
    var blob = new Blob([s2ab(wbout)], {
        type: "application/octet-stream"
    }); // 字符串转ArrayBuffer
    function s2ab(s) {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;
    }
    return blob;
}

function openDownloadDialog(url, saveName) {
    if (typeof url == 'object' && url instanceof Blob) {
        url = URL.createObjectURL(url); // 创建blob地址
    }
        var xhr = new XMLHttpRequest();
        xhr.open('get', url, true); 
        xhr.responseType = "blob"; // 返回类型blob
        // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
        xhr.onload = function() {
            // 请求完成
            if(this.status === 200) {
                // 返回200
            var blob = this.response;
            var href = window.URL.createObjectURL(blob); //创建下载的链接
            //判断是否是IE浏览器，是的话返回true
            if (window.navigator.msSaveBlob) {
              try {
                window.navigator.msSaveBlob(blob, saveName)
              } catch (e) {
                console.log(e);
              }
            } else {
              // 谷歌浏览器 创建a标签 添加download属性下载
              var downloadElement = document.createElement('a');
              downloadElement.href = href;
              downloadElement.target = '_blank';
              downloadElement.download = saveName; //下载后文件名
              document.body.appendChild(downloadElement);
              downloadElement.click(); //点击下载
              document.body.removeChild(downloadElement); //下载完成移除元素
              window.URL.revokeObjectURL(href); //释放掉blob对象
            }
                }
            }
        // 发送ajax请求
        xhr.send()
    var event;
    if (window.MouseEvent)
        {event = new MouseEvent('click');}
    else {
        event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
    }
    aLink.dispatchEvent(event);
}

if (typeof MouseEvent !== 'function') {
    (function (){
        var _MouseEvent = window.MouseEvent;
        window.MouseEvent = function (type, dict){
            dict = dict | {};
            var event = document.createEvent('MouseEvents');
            event.initMouseEvent(
                    type,
                    (typeof dict.bubbles == 'undefined') ? true : !!dict.bubbles,
                    (typeof dict.cancelable == 'undefined') ? false : !!dict.cancelable,
                    dict.view || window,
                    dict.detail | 0,
                    dict.screenX | 0,
                    dict.screenY | 0,
                    dict.clientX | 0,
                    dict.clientY | 0,
                    !!dict.ctrlKey,
                    !!dict.altKey,
                    !!dict.shiftKey,
                    !!dict.metaKey,
                    dict.button | 0,
                    dict.relatedTarget || null
            );
            return event;
        }
    })();
}


function pad2(n) { return n < 10 ? '0' + n : n }

 

function generateTimeReqestNumber() {

    var date = new Date();

    return date.getFullYear().toString() + pad2(date.getMonth() + 1) + pad2(date.getDate()) + pad2(date.getHours()) + pad2(date.getMinutes()) + pad2(date.getSeconds());

}
