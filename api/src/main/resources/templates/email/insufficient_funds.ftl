<#assign title = "Insufficient Funds">
<#assign useJs = false>
<#include "_header.ftl">
<div id="notifications-page">
	<div class="saluation">
		<h4>Dear ${name} ${bank} Customer:</h4>
	</div>
	<h3>${title}</h3>
	<div id="notification">
		<p>We're writing to let you know that there are insufficient funds to complete recent activity for your deposit account ending in ${account}.</p>
		<p>To see a detailed notice about this situation, please check your account. </p>
	</div>
</div>
<#include "_footer.ftl">