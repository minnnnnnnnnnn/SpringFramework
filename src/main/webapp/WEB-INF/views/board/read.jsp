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
<!-- 댓글 -->
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

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    const replyForm = document.querySelector("#replyForm")

    document.querySelector(".addReplyBtn").addEventListener("click", e => {

        e.preventDefault()
        e.stopPropagation()

        const formData = new FormData(replyForm)

        axios.post("/replies", formData).then(res => {
            console.log("--------server response--------")
            console.log(res)
            replyForm.reset()
        })
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
