<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Approve Courses</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Approve Courses</h2>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name course</th>
                    <th>Thời lượng</th>
                    <th>Báo cáo</th>
                    <th>Image</th>
                    <th>Mô tả</th>
                    <th>Price</th>
                    <th>Giá khuyến mãi</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Java Programming</td>
                    <td>30 hours</td>
                    <td>Report 1</td>
                    <td><img src="uploads/java.png" alt="Java Course" style="width: 100px; height: auto;"></td>
                    <td>Learn the basics of Java programming.</td>
                    <td>$200</td>
                    <td>$150</td>
                    <td>
                        <select class="form-select">
                            <option value="pending">Pending</option>
                            <option value="approved">Approved</option>
                            <option value="rejected">Rejected</option>
                        </select>
                    </td>
                    <td>
                        <button class="btn btn-success">Update</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Web Development</td>
                    <td>40 hours</td>
                    <td>Report 2</td>
                    <td><img src="uploads/webdev.png" alt="Web Development" style="width: 100px; height: auto;"></td>
                    <td>Master the art of web development.</td>
                    <td>$250</td>
                    <td>$200</td>
                    <td>
                        <select class="form-select">
                            <option value="pending">Pending</option>
                            <option value="approved">Approved</option>
                            <option value="rejected">Rejected</option>
                        </select>
                    </td>
                    <td>
                        <button class="btn btn-success">Update</button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Database Management</td>
                    <td>35 hours</td>
                    <td>Report 3</td>
                    <td><img src="uploads/database.png" alt="Database Management" style="width: 100px; height: auto;"></td>
                    <td>Learn how to manage databases effectively.</td>
                    <td>$180</td>
                    <td>$140</td>
                    <td>
                        <select class="form-select">
                            <option value="pending">Pending</option>
                            <option value="approved">Approved</option>
                            <option value="rejected">Rejected</option>
                        </select>
                    </td>
                    <td>
                        <button class="btn btn-success">Update</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</body>

</html>
