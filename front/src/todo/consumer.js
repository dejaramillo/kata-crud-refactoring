const HOST_API = window._env.HOST_API || "http://localhost:8080/api/"
export default {
    findAll : async () => {
        return fetch(HOST_API + listId+"/todos")
            .catch(error => console.error('Error:', error))

    },

    save : async (request) => {
        return fetch(HOST_API + listId+"/todo", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error('Error:', error))

    },

    update : async (request) => {
        return fetch(HOST_API + listId+"/todo", {
            method: "PUT",
            body: JSON.stringify(request),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error('Error:', error))

    },

    delete : async (id) => {
        return fetch(HOST_API + id+"/todo", {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .catch(error => console.error('Error:', error))
    },
};