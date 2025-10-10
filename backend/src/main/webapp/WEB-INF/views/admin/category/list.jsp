<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1 class="mb-4">Category Management</h1>
    <a href="${pageContext.request.contextPath}/admin/category/create" class="btn btn-primary mb-3">Create</a>

    <c:if test="${not empty successMessage}">
        <div id="flashMessage" class="alert alert-success">
            <p>${successMessage}</p>
        </div>
    </c:if>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>${category.description}</td>
                <td>
                    <c:choose>
                        <c:when test="${category.status == true}">Active</c:when>
                        <c:otherwise>Inactive</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/category/edit/${category.id}" class="btn btn-warning btn-sm">Edit</a>
                    <button class="btn btn-danger btn-sm delete-btn" data-id="${category.id}">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    window.onload = function() {
        setTimeout(function() {
            var flashMessage = document.getElementById('flashMessage');
            if (flashMessage) {
                flashMessage.style.transition = "opacity 0.5s ease-out";
                flashMessage.style.opacity = "0";
                setTimeout(function() {
                    flashMessage.style.display = "none";
                }, 500);
            }
        }, 3000);

        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', function () {
                var categoryId = this.getAttribute('data-id');
                if (confirm("Are you sure you want to delete this category?")) {
                    fetch('${pageContext.request.contextPath}/admin/category/delete/' + categoryId, {
                        method: 'DELETE'
                    }).then(response => {
                        if (response.ok) {
                            location.reload();
                        } else {
                            alert('Error deleting category');
                        }
                    }).catch(error => console.error('Error:', error));
                }
            });
        });
    };
</script>
