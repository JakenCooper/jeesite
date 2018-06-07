
  	<c:if test="${!empty reqResult}">
  		<c:if test="${reqResult.code == 1}">
  			<script type="text/javascript">
  				alert('${reqResult.msg}');
  			</script>
  		</c:if>
  	</c:if>
