function searchFieldChange(field)
{
	
	var fieldValue = field.value;
	
	$("#searchType").attr("name",fieldValue);
	$("#form").attr("action","find-book-by-"+fieldValue);
	$("#field").empty();
	
	if(fieldValue == "isbn")
	{
		fieldValue = "ISBN";
	}else
		{
			fieldValue = capitalizeFirstLetter(fieldValue);
		}
	
	
	$("#field").append(fieldValue);
	
	
}

	function capitalizeFirstLetter(field)
	{
		return field[0].toUpperCase() + field.slice(1);
	}
	