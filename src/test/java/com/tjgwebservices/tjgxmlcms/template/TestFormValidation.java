package com.tjgwebservices.tjgxmlcms.template;

import com.tjgwebservices.tjgxmlcms.services.FormValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestFormValidation {

	@Test
        @Disabled
	void testDateValidation() {
            boolean invalidDate = FormValidation.dateValidation("baddate");
            Assertions.assertFalse(invalidDate);
            boolean validDate = FormValidation.dateValidation("01/01/2020");
            Assertions.assertTrue(validDate);
	}
    
}
