package com.app.restapi.jpa.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class StudentRegNumberGenerator extends SequenceStyleGenerator {

    public static final String PREFIX = "STD-";

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Fetch the next long value from the sequence
        Long nextVal = (Long) super.generate(session, object);

        // Return formatted string: REG-0001
        return PREFIX + String.format("%04d", nextVal);
    }
}
