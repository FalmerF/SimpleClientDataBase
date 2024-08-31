# SimpleClientDataBase

Простой пример небольшого сервиса для работы с данными клиентов.

Пример использования предоставлен в главном классе `SimpleClientDataBaseApplication`.

## Endpoints

`ClientData addNewClient(String name)` - добавление нового клиента по имени в базу. Возвращает данные клиента.

`ClientData addNewClient(ClientData data)` - добавление нового клиента в базу. Возвращает данные клиента.

`ClientData getClientData(long id)` - получение данных клиента по его Id.

`List<ClientData> getAllClients()` - получение списка всех клиентов.

`void addClientContact(long id, ContactType type, String... contact)` - добавление клиенту новых контактов по типу.

`Set<String> getClientContacts(long id)` - получение списка всех контактов клиента по его Id.

`Set<String> getClientContactsByType(long id, ContactType type)` - получение списка контактов клиента по типу и его Id.