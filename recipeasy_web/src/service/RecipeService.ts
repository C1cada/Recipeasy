// import axios from 'axios';
import { Ingredient } from '../objects/Ingredient';
import Controller from './Controller';
import FetchController from './FetchController';

const API_URL = 'http://localhost:8080/api/v0';
const controller: Controller = new FetchController();

export const getIngredients = async (): Promise<Ingredient[]> => {
    return controller.get(`${API_URL}/ingredients`);
}

export const getIngredient = async (key: string): Promise<Ingredient[]> => {
    return controller.get(`${API_URL}/ingredients/${key}`);
}