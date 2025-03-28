set -e

# Crear múltiples bases de datos
for db in personasclientes cuentas_movimientos; do
    echo "Creando base de datos '$db'"
    psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
        CREATE DATABASE "$db";
EOSQL
done
