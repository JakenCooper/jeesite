<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty model.nps}">
	<c:forEach items="${model.nps}" var="nodePriv">
		<tr>
			<td>${nodePriv.busColumnName}</td>
			<td>
				<c:choose>
					<c:when test="${nodePriv.operCanRead==true}">
						<input type="checkbox"  checked/>  允许读取
					 </c:when>
					<c:otherwise>
						<input type="checkbox"/>  允许读取
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				<c:choose>
					<c:when test="${nodePriv.operCanWrite==true}">
						<input type="checkbox"  checked/>  可以编辑
					 </c:when>
					<c:otherwise>
						<input type="checkbox"/>  可以编辑
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
</c:if>
