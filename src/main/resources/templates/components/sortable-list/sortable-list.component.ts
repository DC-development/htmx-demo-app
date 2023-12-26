export class SortableListComponent {
    private x: string;
    constructor(x: string) {
        this.x = x;
    }
    public getX = (): string => this.x
}