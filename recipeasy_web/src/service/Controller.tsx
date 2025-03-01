abstract class Controller{
    abstract get(str: string): Promise<any>;
    abstract post(str: string, obj: any): Promise<any>;
    abstract put(str: string, obj: any): Promise<any>;
    abstract delete(str: string): Promise<any>;
} export default Controller;