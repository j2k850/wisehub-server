<#assign useJs = false>
<#include "_header.ftl">
<div id="notifications-page">
	<div class="saluation">
		<h1>Hi ${name}</h1>
	</div>
	
	<!-- Heading -->
	<div id="heading">
		<h3>Here’s how you’re doing.</h3>
		<p>Your activity, budgets, and personalized recommendations for the last 7 days.</p>
		<h3> WHERE YOUR MONEY WENT</h3>
		<p>You spent ${expenses.totalSpent} in the last week. </p>
	</div>
	
	<!-- Weekly Summary Graphic -->
	<div id="graphic">
		<img src=""/>
	</div>
	
	<!-- Accounts -->
	<div id="accounts">
	<div class="title">
    		<h3>Accounts</h3>
    	</div>
	<div class="cash">
		<div class="title"><h3>Cash</h3></div>
		<div class="total">${earnings.cashTotal}</div>
		
		<div class="items">
		<ul>
    			<#list cashItems as cashItem>
      			<li>${cashItem.name} - ${cashItem.val}</li>
    			</#list>
  		</ul>
		</div>
	</div>
	
	<div class="investments">
		<div class="title"><h3>Investments</h3></div>
		<div class="total">${earnings.investmentsTotal}</div>
		
		<div class="items">
		<ul>
    			<#list investmentItems as investmentItem>
      			<li>${investmentItem.name} - ${investmentItem.val}</li>
    			</#list>
  		</ul>
		</div>
	<div>
	
	<div class="loans">
		<div class="title"><h3>Loans</h3></div>
		<div class="total">${expenses.loansTotal}</div>
		
		<div class="items">
		<ul>
    			<#list loanItems as loanItem>
      			<li>${loanItem.name} - ${loanItem.val}</li>
    			</#list>
  		</ul>
		</div>
	<div>
</div>
<#include "_footer.ftl">

