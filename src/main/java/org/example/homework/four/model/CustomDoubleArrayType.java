package org.example.homework.four.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

public class CustomStringArrayType implements UserType<Double[]> {
    @Override
    public int getSqlType() {
        return Types.ARRAY;
    }

    @Override
    public Class<Double[]> returnedClass() {
        return Double[].class;
    }

    @Override
    public boolean equals(Double[] x, Double[] y) {
        if (x == null) {
            return y == null;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Double[] doubles) {
        return doubles.hashCode();
    }

    @Override
    public Double[] nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        Array array = resultSet.getArray(i);
        return array != null ? (Double []) array.getArray() : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Double[] doubles, int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

        if (preparedStatement != null) {
            if (doubles != null) {
                Array array = sharedSessionContractImplementor.getJdbcConnectionAccess()
                        .obtainConnection().createArrayOf("double", doubles);
                preparedStatement.setArray(i, array);
            } else {
                preparedStatement.setNull(i, Types.ARRAY);
            }
        }
    }

    @Override
    public Double[] deepCopy(Double[] doubles) {
        return doubles;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Double[] doubles) {
        return (Double[]) this.deepCopy(doubles);
    }

    @Override
    public Double[] assemble(Serializable serializable, Object o) {
        return this.deepCopy((Double[]) o);
    }

    @Override
    public Double[] replace(Double[] original, Double[] target, Object owner) {
        return original;
    }
}
