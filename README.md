# Game Roulette CLJ

Game Roulette is a basic game that allows users to bet coins on random colors in a roulette. It provides a simple and enjoyable gambling experience.

- Users can place bets on different colors in the roulette.

- The roulette will spin and randomly select a winning color.

# Configure
Configure the database connection settings by modifying the flyway.conf file. Set the appropriate values for your database connection, such as host, port, username, and password.

Set the following environment variables related to the database:

- DB_NAME: Name of the database
- DB_PORT: Port number for the database connection
- DB_USER: Username for the database
- DB_PASSWORD: Password for the database user

# Docker

If you are using Docker for local development, the repository provides a docker-compose.yml file that sets up a basic PostgreSQL database. Run the following command to start the database: