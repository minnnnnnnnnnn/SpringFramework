<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-lg-12">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 fw-bold text-primary">Board Read</h6>
            </div>
            <div class="card-body">
                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">Bno</span>
                    <input type="text" class="form-control" value="<c:out value='${board.bno}'/>" readonly>
                </div>

                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">Title</span>
                    <input type="text" name="title" class="form-control" value="<c:out value='${board.title}'/>" readonly>
                </div>

                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">Content</span>
                    <textarea class="form-control" name="content" rows="3" readonly> <c:out value='${board.content}'/></textarea>
                </div>

                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">Writer</span>
                    <input type="text" name="writer" class="form-control" value="<c:out value='${board.writer}'/>" readonly>
                </div>

                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">RegDate</span>
                    <input type="text" name="regDate" class="form-control" value="<c:out value='${board.createdDate}'/>" readonly>
                </div>

                <div class="float-end">
                    <a href='/board/list'>
                        <button type="button" class="btn btn-info btnList" >LIST</button>
                    </a>

                    <c:if test="${!board.delFlag}">
                        <a href='/board/modify/${board.bno}'>
                            <button type="button" class="btn btn-warning btnModify" >MODIFY</button>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 댓글 추가 -->
<div class="col-lg-12">
    <div class="card shadow mb-4">
        <div class="m-4">
            <form id="replyForm" class="mt-4">
                <input type="hidden" name="bno" value="${board.bno}" />

                <div class="mb-3 input-group input-group-lg">
                    <span class="input-group-text">Replyer</span>
                    <input type="text" name="replyer" class="form-control" required />
                </div>

                <div class="mb-3 input-group">
                    <span class="input-group-text">Reply Text</span>
                    <textarea type="text" name="replyText" class="form-control" rows="3" required></textarea>
                </div>

                <div class="text-end">
                    <button type="submit" class="btn btn-primary addReplyBtn">Submit Reply</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 댓글 목록 -->
<div class="col-lg-12">
    <div class="card shadow mb-4">
        <div class="m-4">
            <ul class="list-group replyList">
                <li class="list-group-item">
                    <div class="d-flex justify-content-between">
                        <div>
                            <strong>번호</strong> - 댓글 내용
                        </div>
                        <div class="text-muted small">
                            작성일
                        </div>
                    </div>
                    <div class="mt-1 text-secondary small">
                        작성자
                    </div>
                </li>
            </ul>
            <!-- 댓글 페이징 -->
            <div aria-label="댓글 페이지 네비게이션" class="mt-4">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">이전</a>
                    </li>

                    <li class="page-item active">
                        <a class="page-link" href="#">1</a>
                    </li>

                    <li class="page-item">
                        <a class="page-link" href="#">2</a>
                    </li>

                    <li class="page-item">
                        <a class="page-link" href="#">3</a>
                    </li>

                    <li class="page-item">
                        <a class="page-link" href="#">다음</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    // 댓글 등록
    const replyForm = document.querySelector("#replyForm")

    document.querySelector(".addReplyBtn").addEventListener("click", e => {

        e.preventDefault()
        e.stopPropagation()

        const formData = new FormData(replyForm)

        axios.post("/replies", formData).then(res => {
            console.log("--------server response--------")
            console.log(res)
            replyForm.reset()

            getReplies(1, true)
        })
    }, false)

    // 댓글 목록 호출
    let currentPage = 1
    let currentSize = 10

    const bno = ${board.bno}

    function getReplies(pageNum, goLast) {

        axios.get(`/replies/\${bno}/list`, {
            params : {
                page: pageNum || currentPage,
                size: currentSize
            }
        }).then(res => {
            const data = res.data
            console.log(data)

            const {totalCount, page, size} = data
            if (goLast && totalCount > (page * size)) {
                const lastPage = Math.ceil(totalCount / size)

                getReplies(lastPage)
            } else {
                currentPage = page
                currentSize = size

                printReplies(data)
            }
        })
    }
    getReplies(1, true)

    // 댓글 출력
    const replyList = document.querySelector(".replyList")

    function printReplies(data) {
        const {replyDtoList, page, size, prev, next, start, end, pageNums} = data

        let liStr = ''

        for (const replyDto of replyDtoList) {
            liStr += `<li class="list-group-item" data-rno="\${replyDto.rno}">
                        <div class="d-flex justify-content-between">
                            <div>
                                <strong>\${replyDto.rno}</strong> -\${replyDto.replyText}
                            </div>
                            <div class="text-muted small">
                                \${replyDto.replyDate}
                            </div>
                        </div>
                        <div class="mt-1 text-secondary small">
                            \${replyDto.replyer}
                        </div>
                    </li>`
        }
        replyList.innerHTML = liStr

        // 댓글 페이징 처리
        let pagingStr = ''

        if (prev) {
            pagingStr += `<li class="page-item">
                            <a class="page-link" href="\${start-1}" tabindex="-1">이전</a>
                        </li>`
        }

        for (let i of pageNums) {
            pagingStr += `<li class="page-item \${i === page ? 'active' : ''}">
                            <a class="page-link" href="\${i}">\${i}</a>
                        </li>`
        }
        if (next) {
            pagingStr += `<li class="page-item">
                            <a class="page-link" href="\${end+1}">다음</a>
                        </li>`
        }
        document.querySelector(".pagination").innerHTML = pagingStr

    }

    // 댓글 페이지 번호 이벤트 처리
    document.querySelector(".pagination").addEventListener("click", e => {
        e.stopPropagation()
        e.preventDefault()

        const target = e.target
        const href = target.getAttribute("href")

        if(!href) {
            return
        }
        console.log(href)
        getReplies(href)

    }, false)

</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
