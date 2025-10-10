<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h1 class="mb-4">User Management</h1>
    <a href="/admin/users/form" class="btn btn-primary mb-3">Create</a>

    <c:if test="${not empty successMessage}">
        <div id="flashMessage" class="alert alert-success">
            <p>${successMessage}</p>
        </div>
    </c:if>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>
                    <a href="/admin/users/${user.id}" class="btn btn-info btn-sm">View</a>
                    <a href="/admin/users/form?id=${user.id}" class="btn btn-warning btn-sm">Edit</a>
                    <button class="btn btn-danger btn-sm delete-btn" data-id="${user.id}">Delete</button>
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
    };

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function() {
            var userId = this.getAttribute('data-id');
            if (confirm("Are you sure you want to delete this user?")) {
                fetch('admin/users/delete/' + userId, {
                    method: 'DELETE'
                }).then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('Error deleting user');
                    }
                }).catch(error => console.error('Error:', error));
            }
        });
    });
</script>
</body>
</html>
