function searchFieldChange(field)
{
	
	var fieldValue = field.value;
	
	
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
	