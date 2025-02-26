interface Instruction {
    id: string;
    description: string;
    step_num: number;
    values: Instruction[];
}