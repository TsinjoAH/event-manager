<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
    <c:forEach var="item" items="${testList}">
        <tr>
            <td>${item.getId()}</td>
            <td>${item.getName()}</td>
        </tr>
    </c:forEach>
</table>
