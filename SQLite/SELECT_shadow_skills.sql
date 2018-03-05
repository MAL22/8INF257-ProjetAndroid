SELECT sk.Name as `Skill`,sk.Effect as `Effect`
	
FROM Shadows as 'sh',Skills as 'sk'
	
WHERE sh.ID = sk.ID_Shadow 
