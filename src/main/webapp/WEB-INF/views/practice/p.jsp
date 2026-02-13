<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Practice Page</title>

    <style>
        /* 공통 버튼 스타일 */
        .menu-btn {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        /* 언어 버튼 위치만 따로 */
        #lang {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        /* 드롭다운 리스트 */
        #list {
            position: absolute;
            top: 60px;
            right: 20px;

            list-style: none;
            margin: 0;
            padding: 0;

            border: 1px solid #ccc;
            background: #fff;
        }

        /* 리스트 안 버튼 */
        #list li button {
            width: 100%;
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            background: white;
            cursor: pointer;
            text-align: left;
        }

        #list li button:hover {
            background: #f0f0f0;
        }

    </style>
</head>
<body>
<button class="menu-btn">${menuMap['0'].content}</button>
<button class="menu-btn">${menuMap['1'].content}</button>
<button class="menu-btn">${menuMap['2'].content}</button>
<button class="menu-btn">${menuMap['3'].content}</button>

<button id="lang" class="menu-btn">${menuMap['lang'].content}</button>
<ul id="list" style="display:none">
    <li><a href="/practice/change?lang=ko">한국어</a></li>
    <li><a href="/practice/change?lang=en">English</a></li>
    <li><a href="/practice/change?lang=ch">中國語</a></li>
</ul>
<script>
    document.getElementById("lang").onclick = () => {
        const list = document.getElementById("list");
        list.style.display = list.style.display === "none" ? "block" : "none";
    };
</script>
</body>
</html>
