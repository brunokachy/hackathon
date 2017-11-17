import { User } from '../model/User';


export class PortalUserRole {
    public id: number
    public portalUser: User;
    public designation: string;
    public active: boolean;

    constructor(
    ) { }

    getId(): number {
        return this.id;
    }
    setId(id: number) {
        this.id = id;
    }

    getUser(): User {
        return this.portalUser;
    }
    setUser(portalUser: User) {
        this.portalUser = portalUser;
    }

    getDesignation(): string {
        return this.designation;
    }
    setDesignation(designation: string) {
        this.designation = designation;
    }

    getActive(): boolean {
        return this.active;
    }
    setActive(active: boolean) {
        this.active = active;
    }
}