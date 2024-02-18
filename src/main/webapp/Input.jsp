<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<style>

</style>

<div class="input" p:placeholder="${param.placeholder}" p:value="${param.value}">
    <label>
        <input type="text" value="${param.value}" placeholder=<%=get(request.getParameter("placeholder"))%>>
    </label>
</div>

<%!
    public static String get(String value) {
        return value == null ? "null" : "\"<< " + value.toUpperCase() + " >>\"";
    }
%>
<script>
    const params = {
        value: ${param.value},
        placeholder: <%=get(request.getParameter("placeholder"))%>
    }
    if (params.placeholder === null) {
        new Error("npe")
    }
</script>