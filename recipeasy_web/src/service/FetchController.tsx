import Controller from "./Controller";

class FetchController extends Controller {
    async get(str: string): Promise<any> {
        const response = await fetch(str);
        return response.json();
    }

    async post(str: string, obj: any): Promise<any> {
        const response = await fetch(str, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(obj),
        });
        return response.json();
    }

    async put(str: string, obj: any): Promise<any> {
        const response = await fetch(str, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(obj),
        });
        return response.json();
    }

    async delete(str: string): Promise<any> {
        const response = await fetch(str, {
            method: 'DELETE',
        });
        return response.json();
    }
} export default FetchController;