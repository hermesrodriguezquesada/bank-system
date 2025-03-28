set -e

host="$1"
shift
cmd="$@"

until pg_isready -h "$host" -p 5432; do
  >&2 echo "Postgres está inactivo - esperando..."
  sleep 2
done

>&2 echo "Postgres está arriba - ejecutando el comando"
exec $cmd
