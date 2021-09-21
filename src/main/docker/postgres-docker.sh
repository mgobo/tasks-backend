docker run --name postgresdb \
-e POSTGRES_PASSWORD=admin \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v /Users/mateusgobo/dockerdata/pgsql-devops:/var/lib/postgresql/data \
-p 5432:5432 \
-d postgres:9.6