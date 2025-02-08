# API de Sistema de Compras

## Índice
1. [Categorías](#categorías)
2. [Clientes](#clientes)
3. [Productos](#productos)
4. [Compras](#compras)
5. [Manejo de Errores](#manejo-de-errores)

## Convenciones Generales
- Base URL: `/api`
- Formato de respuesta: JSON
- Autenticación: Bearer Token
- Códigos de estado HTTP estándar

## Categorías

### Endpoints
#### `GET /api/category/all`
Obtiene todas las categorías.
```json
[
  {
    "categoryId": "UUID",
    "description": "string",
    "isActive": "boolean"
  }
]
```

#### `POST /api/category/add`
Crea una nueva categoría.
- **Body**:
```json
{
  "description": "string",
  "isActive": "boolean"
}
```

#### `PATCH /api/category/update`
Actualiza una categoría existente.
- **Body**:
```json
{
  "categoryId": "UUID",
  "description": "string",
  "isActive": "boolean"
}
```

#### `DELETE /api/category/delete`
Elimina una categoría.
- **Body**:
```json
{
  "categoryId": "UUID"
}
```

### Errores Específicos
| Código | Mensaje | Descripción |
|--------|----------|-------------|
| 409 | "La Categoria con la descripcion: {description} ya existe" | Al intentar crear una categoría duplicada |
| 404 | "La categoria con la descripcion: {description} no existe" | Al intentar actualizar una categoría inexistente |

## Clientes

### Endpoints
#### `GET /api/client/all`
Obtiene todos los clientes registrados.
```json
[
  {
    "clientId": "UUID",
    "name": "string",
    "lastname": "string",
    "telephoneNumber": "string",
    "address": "string",
    "mail": "string"
  }
]
```

#### `POST /api/client/add`
Registra un nuevo cliente.
- **Body**:
```json
{
  "name": "string",
  "lastname": "string",
  "telephoneNumber": "string",
  "address": "string",
  "mail": "string"
}
```

#### `PATCH /api/client/update`
Actualiza la información de un cliente existente.
- **Body**:
```json
{
  "clientId": "UUID",
  "name": "string",
  "lastname": "string",
  "telephoneNumber": "string",
  "address": "string",
  "mail": "string"
}
```

### Errores Específicos
| Código | Mensaje | Descripción |
|--------|----------|-------------|
| 409 | "El cliente con el email: {email} ya existe" | Al intentar registrar un email duplicado |
| 404 | "El cliente con el email: {email} no existe" | Al intentar actualizar un cliente inexistente |

## Productos

### Endpoints
#### `GET /api/product/all`
Obtiene todos los productos disponibles.
```json
[
  {
    "productId": "UUID",
    "name": "string",
    "barcode": "string",
    "price": "number",
    "stock": "integer",
    "isActive": "boolean",
    "categoryId": "UUID",
    "categoryDTO": {
      "categoryId": "UUID",
      "description": "string",
      "isActive": "boolean"
    }
  }
]
```

#### `POST /api/product/add`
Registra un nuevo producto.
- **Body**:
```json
{
  "name": "string",
  "barcode": "string",
  "price": "number",
  "stock": "integer",
  "isActive": "boolean",
  "categoryId": "UUID"
}
```

#### `PATCH /api/product/update`
Actualiza la información de un producto existente.
- **Body**:
```json
{
  "productId": "UUID",
  "name": "string",
  "barcode": "string",
  "price": "number",
  "stock": "integer",
  "isActive": "boolean",
  "categoryId": "UUID"
}
```

#### `GET /api/product/find/{productId}`
Obtiene un producto específico por su ID.
- **Parámetros de ruta**: `productId` (UUID)

#### `DELETE /api/product/delete/{productId}`
Elimina un producto existente.
- **Parámetros de ruta**: `productId` (UUID)

#### `GET /api/product/category/{categoryId}`
Obtiene todos los productos de una categoría específica.
- **Parámetros de ruta**: `categoryId` (UUID)

### Errores Específicos
| Código | Mensaje | Descripción |
|--------|----------|-------------|
| 409 | "El producto con el barcode: {barcode} ya existe" | Al intentar crear un producto con código duplicado |
| 404 | "El producto {name} no existe" | Al intentar actualizar un producto inexistente |
| 404 | "La categoria con el id: {categoryId} no existe" | Al asociar un producto a una categoría inexistente |

## Compras

### Endpoints
#### `GET /api/shopping/all`
Obtiene la lista de todas las compras realizadas.
```json
[
  {
    "shoppingId": "UUID",
    "clientId": "UUID",
    "paymentType": "string",
    "comment": "string",
    "isActive": "boolean",
    "purchases": [
      {
        "productId": "UUID",
        "quantity": "integer",
        "total": "number",
        "isActive": "boolean"
      }
    ]
  }
]
```

#### `POST /api/shopping/add`
Registra una nueva compra.
- **Body**:
```json
{
  "clientId": "UUID",
  "paymentType": "string",
  "comment": "string",
  "isActive": "boolean",
  "purchases": [
    {
      "productId": "UUID",
      "quantity": "integer",
      "total": "number",
      "isActive": "boolean"
    }
  ]
}
```

#### `GET /api/shopping/client/{clientId}`
Obtiene todas las compras realizadas por un cliente específico.
- **Parámetros de ruta**: `clientId` (UUID)

### Errores Específicos
| Código | Mensaje | Descripción |
|--------|----------|-------------|
| 404 | "El cliente no existe" | Al crear una compra con cliente inexistente |
| 404 | "El producto no existe" | Al incluir un producto inexistente en la compra |
| 400 | "Stock no disponible para el producto: {name}" | Cuando no hay suficiente stock |

## Manejo de Errores

### Estructura de Error
```json
{
  "timestamp": "2024-03-21T12:00:00Z",
  "status": "number",
  "error": "string",
  "reason": "string",
  "message": "string",
  "path": "string"
}
```

### Códigos de Estado
- 200: Operación exitosa
- 201: Recurso creado exitosamente
- 400: Solicitud incorrecta
- 404: Recurso no encontrado
- 409: Conflicto (duplicación)
- 500: Error interno del servidor

### Razones de Error Comunes
- ENTITY_NOT_FOUND: El recurso solicitado no existe
- ENTITY_EXISTS: Intento de crear un recurso duplicado
- CLIENT_WITH_EMAIL_EXISTS: Email de cliente duplicado
- PRODUCT_STOCK_UNAVAILABLE: Stock insuficiente

## Modelos de Datos

### CategoryDTO
| Campo | Tipo | Descripción |
|-------|------|-------------|
| categoryId | UUID | Identificador único |
| description | String | Descripción de la categoría |
| isActive | Boolean | Estado de la categoría |

### ClientDTO
| Campo | Tipo | Descripción |
|-------|------|-------------|
| clientId | UUID | Identificador único del cliente |
| name | String | Nombre del cliente |
| lastname | String | Apellido del cliente |
| telephoneNumber | String | Número telefónico del cliente |
| address | String | Dirección del cliente |
| mail | String | Correo electrónico del cliente |

### ProductDTO
| Campo | Tipo | Descripción |
|-------|------|-------------|
| productId | UUID | Identificador único del producto |
| name | String | Nombre del producto |
| barcode | String | Código de barras del producto |
| price | Double | Precio del producto |
| stock | Integer | Cantidad disponible en inventario |
| isActive | Boolean | Estado del producto |
| categoryId | UUID | Identificador de la categoría asociada |
| categoryDTO | CategoryDTO | Objeto con la información de la categoría |

### ShoppingDTO
| Campo | Tipo | Descripción |
|-------|------|-------------|
| shoppingId | UUID | Identificador único de la compra |
| clientId | UUID | Identificador del cliente que realizó la compra |
| paymentType | String | Método de pago utilizado |
| comment | String | Comentarios adicionales sobre la compra |
| isActive | Boolean | Estado de la compra |
| purchases | List<PurchaseItemDTO> | Lista de items comprados |

### PurchaseItemDTO
| Campo | Tipo | Descripción |
|-------|------|-------------|
| productId | UUID | Identificador del producto comprado |
| quantity | Integer | Cantidad comprada del producto |
| total | Double | Monto total del item |
| isActive | Boolean | Estado del item de compra |

