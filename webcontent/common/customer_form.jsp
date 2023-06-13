
	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">E-mail :</label>
		<div class="col-sm-8"><input type="email" class="form-control"
			required minlength="10" maxlength="64" name="email"
			value="${customer.email}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">First Name :</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="3" maxlength="30" name="firstname"
			value="${customer.firstname}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Last Name :</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="3" maxlength="30" name="lastname"
			value="${customer.lastname}" /></div>
	</div>
	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Password :</label>
		<div class="col-sm-8"><input type="password" id="password" class="form-control"
			required minlength="4" maxlength="16" name="password"
			value="${customer.password}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Confirm Password :</label>
		<div class="col-sm-8"><input type="password" oninput="checkPasswordMatch(this)"
			required minlength="4" maxlength="16" class="form-control"
			name="confirmpassword" value="${customer.password}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Phone Number :</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="10" maxlength="15" name="phoneNumber"
			value="${customer.phoneNumber}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Address Line 1:</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="10" maxlength="128" name="address1"
			value="${customer.addressLine1}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Address Line 2:</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="10" maxlength="128" name="address2"
			value="${customer.addressLine2}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">City :</label>
		<div class="col-sm-8"><input type="text" class="form-control" required
			minlength="3" maxlength="32" name="city" value="${customer.city}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">State :</label>
		<div class="col-sm-8"><input type="text" class="form-control" required
			minlength="3" maxlength="45" name="state" value="${customer.state}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Zip Code :</label>
		<div class="col-sm-8"><input type="text" class="form-control"
			required minlength="3" maxlength="24" name="zipCode"
			value="${customer.zipCode}" /></div>
	</div>

	<div class="form-group row">
		<label class="col-sm-4 col-form-lable">Country :</label>
		<div class="col-sm-8"><select name="country" required class="form-control">
				<c:forEach items="${mapCountries}" var="country">
					<option value="${country.value}"
						<c:if test='${customer.country eq country.value}'>selected='selected'</c:if>>${country.key}</option>
				</c:forEach>
		</select></div>
	</div>

	<div class="row">&nbsp;</div>

	<div class="row">
		<div class="col text-center">
			<button type="submit" class="btn btn-primary mr-3">Save</button> 
			<button type="button" class="btn btn-secondary" onclick="history.go(-1);">Cancel</button>
		</div>
	</div>