
/**
 * ClassName: BooleanType
 * Package: liquibase.datatype.core
 * Description:
 *
 * @Author ZackCao
 * @Create 2023/10/12 16:33
 * @Version 1.0
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package liquibase.datatype.core;

import liquibase.change.core.LoadDataChange.LOAD_DATA_TYPE;
import liquibase.database.Database;
import liquibase.database.core.*;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.exception.UnexpectedLiquibaseException;
import liquibase.statement.DatabaseFunction;
import liquibase.util.StringUtil;

import java.util.Locale;

@DataTypeInfo(
        name = "boolean",
        aliases = {"java.sql.Types.BOOLEAN", "java.lang.Boolean", "bit", "bool"},
        minParameters = 0,
        maxParameters = 0,
        priority = 1
)
public class BooleanType extends LiquibaseDataType {
    public BooleanType() {
    }

    @Override
    public DatabaseDataType toDatabaseDataType(Database database) {
        String originalDefinition = StringUtil.trimToEmpty(getRawDefinition());
        if ((database instanceof Firebird3Database)) {
            return new DatabaseDataType("BOOLEAN");
        }

        if ((database instanceof AbstractDb2Database) || (database instanceof FirebirdDatabase)) {
            return new DatabaseDataType("SMALLINT");
        } else if (database instanceof MSSQLDatabase) {
            return new DatabaseDataType(database.escapeDataTypeName("bit"));
        } else if (database instanceof MySQLDatabase) {
            if (originalDefinition.toLowerCase(Locale.US).startsWith("bit")) {
                return new DatabaseDataType("BIT", getParameters());
            }
            return new DatabaseDataType("BIT", 1);
        } else if (database instanceof OracleDatabase) {
            return new DatabaseDataType("NUMBER", 1);
        } else if ((database instanceof SybaseASADatabase) || (database instanceof SybaseDatabase)) {
            return new DatabaseDataType("BIT");
        } else if (database instanceof DerbyDatabase) {
            if (((DerbyDatabase) database).supportsBooleanDataType()) {
                return new DatabaseDataType("BOOLEAN");
            } else {
                return new DatabaseDataType("SMALLINT");
            }
        } else if (database.getClass().isAssignableFrom(DB2Database.class)) {
            if (((DB2Database) database).supportsBooleanDataType())
                return new DatabaseDataType("BOOLEAN");
            else
                return new DatabaseDataType("SMALLINT");
        } else if (database instanceof HsqlDatabase) {
            return new DatabaseDataType("BOOLEAN");
        } else if (database instanceof PostgresDatabase) {
            if (originalDefinition.toLowerCase(Locale.US).startsWith("bit")) {
                return new DatabaseDataType("BIT", getParameters());
            }
        } else if(database instanceof DmDatabase) {
            return new DatabaseDataType("bit");
        }

        return super.toDatabaseDataType(database);
    }

    @Override
    public String objectToSql(Object value, Database database) {
        if (value != null && !"null".equals(value.toString().toLowerCase(Locale.US))) {
            String returnValue;
            if (value instanceof String) {
                value = ((String)value).replaceAll("'", "");
                if (!"true".equals(((String)value).toLowerCase(Locale.US)) && !"1".equals(value) && !"b'1'".equals(((String)value).toLowerCase(Locale.US)) && !"t".equals(((String)value).toLowerCase(Locale.US)) && !((String)value).toLowerCase(Locale.US).equals(this.getTrueBooleanValue(database).toLowerCase(Locale.US))) {
                    if (!"false".equals(((String)value).toLowerCase(Locale.US)) && !"0".equals(value) && !"b'0'".equals(((String)value).toLowerCase(Locale.US)) && !"f".equals(((String)value).toLowerCase(Locale.US)) && !((String)value).toLowerCase(Locale.US).equals(this.getFalseBooleanValue(database).toLowerCase(Locale.US))) {
                        throw new UnexpectedLiquibaseException("Unknown boolean value: " + value);
                    }

                    returnValue = this.getFalseBooleanValue(database);
                } else {
                    returnValue = this.getTrueBooleanValue(database);
                }
            } else if (value instanceof Long) {
                if (Long.valueOf(1L).equals(value)) {
                    returnValue = this.getTrueBooleanValue(database);
                } else {
                    returnValue = this.getFalseBooleanValue(database);
                }
            } else if (value instanceof Number) {
                if (!value.equals(1) && !"1".equals(value.toString()) && !"1.0".equals(value.toString())) {
                    returnValue = this.getFalseBooleanValue(database);
                } else {
                    returnValue = this.getTrueBooleanValue(database);
                }
            } else {
                if (value instanceof DatabaseFunction) {
                    return value.toString();
                }

                if (!(value instanceof Boolean)) {
                    throw new UnexpectedLiquibaseException("Cannot convert type " + value.getClass() + " to a boolean value");
                }

                if ((Boolean)value) {
                    returnValue = this.getTrueBooleanValue(database);
                } else {
                    returnValue = this.getFalseBooleanValue(database);
                }
            }

            return returnValue;
        } else {
            return null;
        }
    }

    protected boolean isNumericBoolean(Database database) {
        if (database instanceof DerbyDatabase) {
            return !((DerbyDatabase) database).supportsBooleanDataType();
        } else if (database.getClass().isAssignableFrom(DB2Database.class)) {
            return !((DB2Database) database).supportsBooleanDataType();
        }
        return (database instanceof Db2zDatabase) || (database instanceof DB2Database) || (database instanceof FirebirdDatabase) || (database instanceof
                MSSQLDatabase) || (database instanceof MySQLDatabase) || (database instanceof OracleDatabase) ||
                (database instanceof SQLiteDatabase) || (database instanceof SybaseASADatabase) || (database instanceof
                SybaseDatabase) || (database instanceof DmDatabase);
    }

    public String getFalseBooleanValue(Database database) {
        if (this.isNumericBoolean(database)) {
            return "0";
        } else {
            return database instanceof InformixDatabase ? "'f'" : "FALSE";
        }
    }

    public String getTrueBooleanValue(Database database) {
        if (this.isNumericBoolean(database)) {
            return "1";
        } else {
            return database instanceof InformixDatabase ? "'t'" : "TRUE";
        }
    }

    @Override
    public LOAD_DATA_TYPE getLoadTypeName() {
        return LOAD_DATA_TYPE.BOOLEAN;
    }
}
