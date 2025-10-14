# Stage 1: build
FROM node:18 AS builder

WORKDIR /app

# Copy package.json và package-lock.json
COPY package*.json ./

# Cài dependencies
RUN npm install

# Copy toàn bộ source
COPY . .

# Build Next.js
RUN npm run build

# Stage 2: production image
FROM node:18

WORKDIR /app

# Copy node_modules và build từ stage 1
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
COPY --from=builder /app/package*.json ./

# Port Next.js mặc định là 3000
ENV PORT=3000
EXPOSE 3000

# Start Next.js server
CMD ["npm", "run", "start"]
